package Model;

import java.util.List;

public class Pawn extends Piece{
    public Pawn(Color color) {
        super(PieceName.PAWN, color, 1);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        List<Position> possibleMoves = new java.util.ArrayList<>();
        return null;
    }
}
