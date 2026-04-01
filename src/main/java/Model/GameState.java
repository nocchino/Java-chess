package Model;

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

        //non mi piace da fixare
        List<Position> possiblePosition=new ArrayList<>();
        int startI=i;
        int startJ=j;
        //All 4 direction
        for (int k = 0; k <4 ; k++) {
            i=startI;
            j=startJ;

            if (k==0){
                while (board.isInBound(i,j)){
                    if (board.isInBound(i+1,j) && board.getPieceAt(i+1,j)==null){
                        i++;
                        possiblePosition.add(new Position(i,j));

                    }else if(board.isInBound(i+1,j) && board.getPieceAt(i+1,j)!=null) {
                        if (board.getPieceAt(i+1,j).getColor()!=piece.getColor()){
                            i++;
                            possiblePosition.add(new Position(i,j));
                            break;
                        }break;
                    } else{
                        break;
                    }
                }
            }


            if (k==1){
                while (board.isInBound(i,j)){
                    if (board.isInBound(i,j+1) && board.getPieceAt(i,j+1)==null){
                        j++;
                        possiblePosition.add(new Position(i,j));

                    }else if(board.isInBound(i,j+1) && board.getPieceAt(i,j+1)!=null) {
                        if (board.getPieceAt(i,j+1).getColor()!=piece.getColor()){
                            j++;
                            possiblePosition.add(new Position(i,j));
                            break;
                        }break;
                    } else{
                        break;
                    }
                }
            }

            if (k==2){
                while (board.isInBound(i,j)){
                    if (board.isInBound(i,j-1) && board.getPieceAt(i,j-1)==null){
                        j--;
                        possiblePosition.add(new Position(i,j));

                    }else if(board.isInBound(i,j-1) && board.getPieceAt(i,j-1)!=null) {
                        if (board.getPieceAt(i,j-1).getColor()!=piece.getColor()){
                            j--;
                            possiblePosition.add(new Position(i,j));
                            break;
                        }break;
                    } else{
                        break;
                    }
                }
            }

            if (k==3){
                while (board.isInBound(i,j)){
                    if (board.isInBound(i-1,j) && board.getPieceAt(i-1,j)==null){
                        i--;
                        possiblePosition.add(new Position(i,j));

                    }else if(board.isInBound(i-1,j) && board.getPieceAt(i-1,j)!=null) {
                        if (board.getPieceAt(i-1,j).getColor()!=piece.getColor()){
                            i--;
                            possiblePosition.add(new Position(i,j));
                            break;
                        }break;
                    } else{
                        break;
                    }
                }
            }

        }

        return possiblePosition;
    }
}