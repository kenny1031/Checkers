package Checkers;

public class Move {
    private Cell destination;
    private CheckersPiece captured;

    public Move(Cell destination, CheckersPiece captured) {
        this.destination = destination;
        this.captured = captured;
    }

    public Cell getDestination() {
        return this.destination;
    }

    public void execute(CheckersPiece moving) {
        if (captured != null) {
            captured.capture();
        }
        moving.move(this.destination);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Move) {
            Move m = (Move)o;
            return m.getDestination() == this.destination;
        }
        if (o instanceof Cell) {
            Cell m = (Cell)o;
            return m == this.destination;
        }
        return false;
    }
}
