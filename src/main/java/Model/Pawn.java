package Model;

public class Pawn extends Piece{
    public Pawn(Color color, int row, int column) {
        super(PieceName.PAWN, color, row, column, 1);
    }
}
