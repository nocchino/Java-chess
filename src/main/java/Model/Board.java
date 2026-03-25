package Model;

public class Board {
    private Piece[][] board=new Piece[8][8];

    public Piece getPieceAt(int r,int c){
        if (!isInBound(r, c)) {
            return null; // Out of bounds
        }
        return board[r][c];
    }

    public boolean isInBound(int r,int c){
        return (r>=0&&r<8)&&(c>=0&&c<8);
    }

    private void setPosition(Piece piece, int r,int c){
        board[r][c]=piece;

    }
    private void setInitialPosition(){
        board[0][0]=new Rook(Color.BLACK);

    }

    public boolean move(int oldRow,int oldColumn, int newRow, int newColumn){
        if (!isInBound(oldRow, oldColumn) || !isInBound(newRow, newColumn)) {
            return false; // Out of bounds
        }if (board[oldRow][oldColumn] == null) {
            return false; // No piece at the old position
        }else {
            Piece movingPiece = board[oldRow][oldColumn];
            //delete old position
            board[oldRow][oldColumn] = null;

            setPosition(movingPiece, newRow, newColumn);
            return true;
        }
    }
}
