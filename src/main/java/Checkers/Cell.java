package Checkers;

public class Cell {

	private int x;
	private int y;
	private CheckersPiece piece;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setPiece(CheckersPiece p) {
		this.piece = p;
		if (p != null) {
			p.setPosition(this);
		}
	}
	
	public CheckersPiece getPiece() {
		return this.piece;
	}
	
	public void draw(App app) {
		//if cell is selected, highlight in green
		boolean drawRect = false;
		if (app.getAvailableMoves().containsKey(this)) {
			app.setFill(2, (x+y) % 2);
			drawRect = true;
		} else if (app.getCurrentSelected() != null && this.getPiece() == app.getCurrentSelected()) {
			app.setFill(1, (x+y) % 2);
			drawRect = true;
		} else if ((x+y) % 2 == 1) {
			//black cell
			app.fill(App.BLACK_RGB[0], App.BLACK_RGB[1], App.BLACK_RGB[2]);
			drawRect = true;
		}
		if (drawRect) {
			app.rect(x*App.CELLSIZE, y*App.CELLSIZE, App.CELLSIZE, App.CELLSIZE);
		}
		if (this.piece != null) {
			//Remove captured pieces from the board
			if (this.piece.isCaptured()) {
				app.getPiecesInPlay().get(this.piece.getColour()).remove(this.piece);
				this.piece = null;
			} else if (!this.piece.isMoving()){
				this.piece.draw(app);
			}
		}
	}
}