package Checkers;

import java.util.HashSet;
import java.util.Set;

public class CheckersPiece {

	private char colour;
	private Cell position;

	private boolean isCaptured;
	private boolean king;
	
	public CheckersPiece(char c) {
		this.colour = c;
	}
	
	public char getColour() {
		return this.colour;
	}
	
	public void setPosition(Cell p) {
		this.position = p;
	}
	
	public Cell getPosition() {
		return this.position;
	}

	/**
	 * Add a move to the potential moves set
	 * @param moves The result collection storing all the moves
	 * @param board The board to search for available moves
	 * @param xOffset The offset in the x direction (number of cells)
	 * @param yOffset The offset in the y direction (number of cells)
	 * @param checkCaptures The piece being jumped over - check if this piece can jump over other pieces in the given direction and if so, also add those moves
	 */
	private void addMove(HashSet<Move> moves, Cell[][] board, int xOffset, int yOffset, CheckersPiece checkCaptures) {
		int x = this.getPosition().getX()+xOffset;
		int y = this.getPosition().getY()+yOffset;
		if (x >= board[0].length || x < 0 || y < 0 || y >= board.length) return;
		if (board[y][x].getPiece() == null) {
			if (checkCaptures != null && checkCaptures.getColour() == this.colour) {
				checkCaptures = null; //player cannot capture their own piece
			}
			moves.add(new Move(board[y][x], checkCaptures));
		} else if (checkCaptures == null) {
			addMove(moves, board, xOffset*2, yOffset*2, board[y][x].getPiece());
		}
	}
	public Set<Move> getAvailableMoves(Cell[][] board) {
		//Get available moves for this piece depending on the board layout, and whether this piece is a king or not
		//How to record if the move is a capture or not? Maybe make a new class 'Move' that stores this information, along with the captured piece?
		HashSet<Move> moves = new HashSet<>();
		if (colour == 'w' || king) {
			addMove(moves, board, 1, 1, null);
			addMove(moves, board, -1, 1, null);
		}
		if (colour == 'b' || king) {
			addMove(moves, board, 1, -1, null);
			addMove(moves, board, -1, -1, null);
		}

		return moves;
	}
	
	public void capture() {
		//capture this piece
		this.isCaptured = true;
	}

	public boolean isCaptured() {
		return this.isCaptured;
	}
	
	public void promote() {
		//promote this piece
		this.king = true;
	}

	//Check if piece should be promoted and promote it
	public void checkPromotion() {
		if ((this.colour == 'w' && this.getPosition().getY() == 7) ||
			(this.colour == 'b' && this.getPosition().getY() == 0)) {
			promote();
		}
	}
	
	//draw the piece
	public void draw(App app) {
		app.strokeWeight(5.0f);
		if (colour == 'w') {
			app.fill(255);
			app.stroke(0);
		} else if (colour == 'b') {
			app.fill(0);
			app.stroke(255);
		}
		app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2 + xOffset, position.getY()*App.CELLSIZE + App.CELLSIZE/2 + yOffset, App.CELLSIZE*0.8f, App.CELLSIZE*0.8f);
		if (king) {
			app.ellipse(position.getX()*App.CELLSIZE + App.CELLSIZE/2 + xOffset, position.getY()*App.CELLSIZE + App.CELLSIZE/2 + yOffset, App.CELLSIZE*0.4f, App.CELLSIZE*0.4f);
		}
		app.noStroke();

		if (this.isMoving > 0) {
			this.xOffset -= this.xDelta;
			this.yOffset -= this.yDelta;
			this.isMoving -= 1;
		}
	}

	public static float speed = 2.0f; //2 pixels per frame
	private float xOffset;
	private float yOffset;
	private float xDelta;
	private float yDelta;
	private int isMoving = 0;
	public void move(Cell destination) {
		//calculate trajectory and begin movement animation
		xOffset = App.CELLSIZE*this.getPosition().getX() - App.CELLSIZE*destination.getX();
		yOffset = App.CELLSIZE*this.getPosition().getY() - App.CELLSIZE*destination.getY();
		double distance = Math.sqrt(Math.pow(xOffset, 2) + Math.pow(yOffset, 2));

		//number of frames the animation should last for:
		this.isMoving = (int)(distance / speed);

		//reduce offset by this much on each frame
		xDelta = xOffset / this.isMoving;
		yDelta = yOffset / this.isMoving;
		this.getPosition().setPiece(null);
		destination.setPiece(this);
	}

	public boolean isMoving() {
		return isMoving > 0;
	}
}