package controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ChessController {
    private Game game;

    private Pane[][] cells = new Pane[8][8]; //queste sono i 64 pane
    @FXML
    private GridPane chessBoard;//chessboard sotto che non fa nulla il gridpane
    private List<Position> possibleMoveHighlight;

    @FXML
    public void initialize(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                Pane pane=new Pane();
                if ((i+j)%2==0){
                    pane.setStyle("-fx-background-color: #F0D9B5;");
                    cells[i][j]=pane;
                }else{
                    pane.setStyle("-fx-background-color: #B58863;");
                    cells[i][j]=pane;
                }
                chessBoard.add(pane,i,j);
            }
        }
        possibleMoveHighlight=new ArrayList<>();
    }

    public void setGame(Game game){
        this.game=game;
        drawPieces();




    }


    public void resetSquareColor(List<Position> currentPossibleMove){
        for (Position position:currentPossibleMove){
            if ((position.getRow()+position.getColumn())%2==0){
                cells[position.getRow()][position.getColumn()].setStyle("-fx-background-color: #F0D9B5;");
            }else{
                cells[position.getRow()][position.getColumn()].setStyle("-fx-background-color: #B58863;");
            }
        }

    }

    public void drawPossibleMove(List<Position> listPossibleMove){
        for (Position position :listPossibleMove){
            cells[position.getRow()][position.getColumn()].setStyle("-fx-background-color: #90EE90;");
        }
    }

    public void drawPieces(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                cells[i][j].getChildren().clear(); //clear all the imageView in the pane and redraw it
                Piece pezzo=game.getGameState().getBoard().getPieceAt(i,j);
                int finalI = i;
                int finalJ = j;
                if (pezzo!=null){
                    if (pezzo.getPieceName() == PieceName.PAWN) {
                        if (pezzo.getColor() == Color.WHITE){
                            Image img = new Image(getClass().getResourceAsStream("/photo/PawnWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setOnMouseClicked(event -> {
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();
                                System.out.println("Clicked on " + pezzo.getPieceName() +  " "+ pezzo.getColor()+ " at " + finalI + "," + finalJ);
                                // You can add more logic here to show possible moves, etc.
                                possibleMoveHighlight=game.getGameState().getPossibleMovePawn(pezzo,finalI,finalJ);
                                drawPossibleMove(possibleMoveHighlight);

                            });
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/PawnBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }

                    }else if (pezzo.getPieceName() == PieceName.ROOK ) {
                        if (pezzo.getColor() == Color.WHITE) {
                            Image img = new Image(getClass().getResourceAsStream("/photo/RookWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setOnMouseClicked(event -> {
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();
                                System.out.println("Clicked on " + pezzo.getPieceName() + " " + pezzo.getColor() + " at " + finalI + "," + finalJ);
                                // You can add more logic here to show possible moves, etc.
                                possibleMoveHighlight = game.getGameState().getPossibleMoveRook(pezzo, finalI, finalJ);
                                drawPossibleMove(possibleMoveHighlight);
                            });
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        } else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/RookBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }
                    }else if (pezzo.getPieceName() == PieceName.KING) {
                        if (pezzo.getColor() == Color.WHITE) {
                            Image img = new Image(getClass().getResourceAsStream("/photo/KingWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        } else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/KingBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }
                    }else if (pezzo.getPieceName() == PieceName.KNIGHT) {
                        if (pezzo.getColor() == Color.WHITE) {
                            Image img = new Image(getClass().getResourceAsStream("/photo/KnightWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setOnMouseClicked(event -> {
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();
                                System.out.println("Clicked on " + pezzo.getPieceName() +  " "+ pezzo.getColor()+ " at " + finalI + "," + finalJ);
                                // You can add more logic here to show possible moves, etc.
                                possibleMoveHighlight=game.getGameState().getPossibleMoveKnight(pezzo,finalI,finalJ);
                                drawPossibleMove(possibleMoveHighlight);
                            });
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        } else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/KnightBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }
                    }else if (pezzo.getPieceName() == PieceName.BISHOP) {
                        if (pezzo.getColor() == Color.WHITE) {
                            Image img = new Image(getClass().getResourceAsStream("/photo/BishopWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setOnMouseClicked(event -> {
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();
                                System.out.println("Clicked on " + pezzo.getPieceName() +  " "+ pezzo.getColor()+ " at " + finalI + "," + finalJ);
                                // You can add more logic here to show possible moves, etc.
                                possibleMoveHighlight=game.getGameState().getPossibleMoveBishop(pezzo,finalI,finalJ);
                                drawPossibleMove(possibleMoveHighlight);
                            });
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        } else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/BishopBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }
                    }else if (pezzo.getPieceName() == PieceName.QUEEN) {
                        if (pezzo.getColor() == Color.WHITE) {
                            Image img = new Image(getClass().getResourceAsStream("/photo/QueenWhite.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setOnMouseClicked(event -> {
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();
                                System.out.println("Clicked on " + pezzo.getPieceName() +  " "+ pezzo.getColor()+ " at " + finalI + "," + finalJ);
                                // You can add more logic here to show possible moves, etc.
                                possibleMoveHighlight=game.getGameState().getPossibleMoveQueen(pezzo,finalI,finalJ);
                                drawPossibleMove(possibleMoveHighlight);
                            });
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        } else {
                            Image img = new Image(getClass().getResourceAsStream("/photo/QueenBlack.png"));
                            ImageView imageView = new ImageView(img);
                            imageView.setFitWidth(65);
                            imageView.setFitHeight(65);
                            cells[i][j].getChildren().add(imageView);
                        }
                    }
                }
            }
        }
    }

    // Nel controller
    public void updateBoard() {

    }

}
