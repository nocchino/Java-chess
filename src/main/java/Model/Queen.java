package Model;

import java.util.List;

public class Queen extends Piece{
    public Queen(Color color) {
        super(PieceName.QUEEN, color, 9);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        return null;
    }
}
