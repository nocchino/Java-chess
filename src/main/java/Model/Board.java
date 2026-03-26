package Model;

public class Board {
    private Piece[][] board=new Piece[8][8];

    public Board(){
        setInitialPosition();
    }

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
        //Rook
        board[0][0]=new Rook(Color.WHITE);
        board[0][7]=new Rook(Color.WHITE);
        board[7][0]=new Rook(Color.BLACK);
        board[7][7]=new Rook(Color.BLACK);

        //Knight
        board[0][1]=new Knight(Color.WHITE);
        board[0][6]=new Knight(Color.WHITE);
        board[7][1]=new Knight(Color.BLACK);
        board[7][6]=new Knight(Color.BLACK);

        //Bishop
        board[0][2]=new Bishop(Color.WHITE);
        board[0][5]=new Bishop(Color.WHITE);
        board[7][2]=new Bishop(Color.WHITE);
        board[7][5]=new Bishop(Color.BLACK);

        //Queen and King
        board[0][3]=new Queen(Color.WHITE);
        board[7][3]=new Queen(Color.BLACK);

        board[0][4]=new King(Color.WHITE);
        board[7][4]=new King(Color.BLACK);

        //Pawn
        for (int i = 0; i < 8; i++) {
            board[1][i]=new Pawn(Color.WHITE);
            board[6][i]=new Pawn(Color.BLACK);

        }


    }

    public boolean move(int oldRow,int oldColumn, int newRow, int newColumn){
        Piece movingPiece = board[oldRow][oldColumn];
        if (movingPiece.getColor()==board[newRow][newColumn].getColor()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }
        if (!isInBound(oldRow, oldColumn) || !isInBound(newRow, newColumn)) {
            return false; // Out of bounds
        }if (board[oldRow][oldColumn] == null) {
            return false; // No piece at the old position
        }else {
            //Delete old position
            board[oldRow][oldColumn] = null;
            setPosition(movingPiece, newRow, newColumn);
            return true;
        }
    }
}
