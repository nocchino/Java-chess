package Model;

import java.util.List;

public class Rook extends Piece{
    public Rook(Color color) {
        super(PieceName.ROOK, color, 5);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        return null;
    }
}
