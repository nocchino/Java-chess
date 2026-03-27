package Model;

import java.util.List;

public class Knight extends Piece{
    public Knight(Color color) {
        super(PieceName.KNIGHT, color, 3);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        return null;
    }
}
