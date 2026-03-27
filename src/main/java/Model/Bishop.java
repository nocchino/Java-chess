package Model;

import java.util.List;

public class Bishop extends Piece{

    public Bishop(Color color) {
        super(PieceName.BISHOP, color, 3);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        return null;
    }
}
