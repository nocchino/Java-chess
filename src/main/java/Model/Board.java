package Model;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Board {
    private Piece[][] board=new Piece[8][8];
    private GameState gameState;
    public Board(GameState gameState){
        setInitialPosition();
        this.gameState=gameState;
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
        board[7][2]=new Bishop(Color.BLACK);
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


    public boolean move(int oldRow, int oldColumn, int newRow, int newColumn) {
        if (!isInBound(oldRow, oldColumn) || !isInBound(newRow, newColumn)) {
            return false;
        }

        Piece movingPiece = board[oldRow][oldColumn];
        if (movingPiece == null) {
            return false;
        }

        Piece targetPiece = board[newRow][newColumn];
        if (targetPiece != null && targetPiece.getColor() == movingPiece.getColor()) {
            return false;
        }

        board[oldRow][oldColumn] = null;
        board[newRow][newColumn] = movingPiece;
        movingPiece.increaseMoveCount();
        return true;
    }



    private String countFreeCells(int riga,int colonna){
        int res=0;
        while (isInBound(riga,colonna)){
            if (board[riga][colonna]==null){
                res+=1;
                colonna++;
            }else{
                break;
            }
        }
        return String.valueOf(res);
    }


    //WHITE CAPITAL
    //black lowercase
    //hardcoded arrocco!!
    //TODO da finire
    public String transformIntoFen(){
        StringBuilder sb=new StringBuilder();
        int colonna;
        int riga=7;
        while(riga>=0){
            colonna=0;
            while (colonna<=7){
                Piece piece=getPieceAt(riga,colonna);
                if (piece==null){
                    String res=countFreeCells(riga,colonna);
                    sb.append(res);
                    colonna+=Integer.parseInt(res)-1;
                }else{
                    switch (piece.getPieceName()) {
                        case KING:
                            sb.append(piece.getColor() == Color.WHITE ? "K" : "k");
                            break;
                        case QUEEN:
                            sb.append(piece.getColor() == Color.WHITE ? "Q" : "q");
                            break;
                        case ROOK:
                            sb.append(piece.getColor() == Color.WHITE ? "R" : "r");
                            break;
                        case BISHOP:
                            sb.append(piece.getColor() == Color.WHITE ? "B" : "b");
                            break;
                        case KNIGHT:
                            sb.append(piece.getColor() == Color.WHITE ? "N" : "n");
                            break;
                        case PAWN:
                            sb.append(piece.getColor() == Color.WHITE ? "P" : "p");
                            break;

                    }
                }

                colonna++;
            }
            sb.append("/");
            riga--;
        }
        String stringaDaTornare=sb.substring(0,sb.length()-1);
        if (gameState.getPlayerTurn()==Color.WHITE){
            stringaDaTornare+=" w ";
        }else{
            stringaDaTornare+=" b ";
        }
        stringaDaTornare+= "KQkq - 0 "+ gameState.getMoveCount();
        return stringaDaTornare;
    }
}
