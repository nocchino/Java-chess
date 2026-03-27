package Model;

import java.util.List;

public class King extends Piece{
    public King(Color color) {
        super(PieceName.KING, color,1000);
    }

    @Override
    public List<Position> getpossibleMove(Board board, Position from) {
        return null;
    }
}
