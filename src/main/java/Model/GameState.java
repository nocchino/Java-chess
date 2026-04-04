package Model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Board board;
    private Color playerTourn;
    private GameStatus status=GameStatus.NOT_STARTED;
    private int moveCount;

    public GameState(){
        board=new Board();
        playerTourn=Color.WHITE;
        status=GameStatus.ONGOING;
        moveCount=0;
    }

    public boolean isFree(int row,int col){
        return board.getPieceAt(row, col) == null;
    }

    public Board getBoard() {
        return board;
    }

    public List<Position> getPossibleMovePawn(Piece piece,int i, int j){
        List<Position> possiblePosition=new ArrayList<>();
        if (!isFree(i+1,j)){
            return possiblePosition;
        }
        if (piece.getMoveCount()==0) {
            possiblePosition.add(new Position(i+2,j));
            possiblePosition.add(new Position(i+1,j));
        }if(piece.getMoveCount()!=0){
            possiblePosition.add(new Position(i+1,j));
        }if (board.getPieceAt(i+1,j+1)!=null &&(board.getPieceAt(i+1,j+1)).getColor()!=piece.getColor() && board.isInBound(i+1,j+1)){
            possiblePosition.add(new Position(i+1,j+1));
        }if (board.getPieceAt(i+1,j-1)!=null && (board.getPieceAt(i+1,j-1)).getColor()!=piece.getColor()&&board.isInBound(i+1,j-1)){
            possiblePosition.add(new Position(i+1,j-1));
        }

        return possiblePosition;
    }

    public List<Position> getPossibleMoveKnight(Piece piece,int i, int j){
        List<Position> possiblePosition=new ArrayList<>();

        int[][] knightOffsets = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };

        for (int[] offset : knightOffsets) {
            int newRow = i + offset[0];
            int newCol = j + offset[1];

            if (!board.isInBound(newRow, newCol)) {
                continue;
            }

            Piece targetPiece = board.getPieceAt(newRow, newCol);
            if (targetPiece == null || targetPiece.getColor() != piece.getColor()) {
                possiblePosition.add(new Position(newRow, newCol));
            }
        }

        return possiblePosition;
    }

    public List<Position> getPossibleMoveRook(Piece piece, int i, int j){
        List<Position> possiblePosition = new ArrayList<>();
        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int[] direction : directions) {
            int nextRow = i + direction[0];
            int nextCol = j + direction[1];

            while (board.isInBound(nextRow, nextCol)) {
                Piece targetPiece = board.getPieceAt(nextRow, nextCol);

                if (targetPiece == null) {
                    possiblePosition.add(new Position(nextRow, nextCol));
                } else {
                    if (targetPiece.getColor() != piece.getColor()) {
                        possiblePosition.add(new Position(nextRow, nextCol));
                    }
                    break;
                }

                nextRow += direction[0];
                nextCol += direction[1];
            }

        }

        return possiblePosition;
    }

    public List<Position> getPossibleMoveBishop(Piece piece, int i, int j){
        List<Position> possiblePosition = new ArrayList<>();
        int[][] directions = {
                {1, 1},
                {1, -1},
                {-1, -1},
                {-1, 1}
        };

        for (int[] direction : directions) {
            int nextRow = i + direction[0];
            int nextCol = j + direction[1];

            while (board.isInBound(nextRow, nextCol)) {
                Piece targetPiece = board.getPieceAt(nextRow, nextCol);

                if (targetPiece == null) {
                    possiblePosition.add(new Position(nextRow, nextCol));
                } else {
                    if (targetPiece.getColor() != piece.getColor()) {
                        possiblePosition.add(new Position(nextRow, nextCol));
                    }
                    break;
                }

                nextRow += direction[0];
                nextCol += direction[1];
            }

        }

        return possiblePosition;
    }

    public List<Position> getPossibleMoveQueen(Piece piece, int i, int j){
        List<Position> possibleMove=new ArrayList<>();

        getPossibleMoveRook(piece,i,j).stream().forEach(n->possibleMove.add(n));
        getPossibleMoveBishop(piece,i,j).stream().forEach(n->possibleMove.add(n));


        return possibleMove;
    }

    public void setNextTurn(){
        if (playerTourn==Color.WHITE){
            playerTourn=Color.BLACK;
        }else {
            playerTourn=Color.WHITE;
        }
    }


    public Color getPlayerTurn(){
        return playerTourn;
    }
}