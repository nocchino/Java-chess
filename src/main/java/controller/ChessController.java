package controller;

import Model.*;
import com.google.gson.Gson;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChessController {
    private Game game;

    private Pane[][] cells = new Pane[8][8]; //queste sono i 64 pane
    @FXML
    private GridPane chessBoard;//chessboard sotto che non fa nulla il gridpane
    private List<Position> possibleMoveHighlight;
    private Piece activePiece;
    private Position currentPosition;

    @FXML
    public void initialize(){
        drawBoard();
        possibleMoveHighlight=new ArrayList<>();
    }

    public void setGame(Game game){
        this.game=game;
        drawPieces();
    }

    public void drawBoard(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                Pane pane=new Pane();
                int finalI=i;
                int finalJ=j;
                if ((i+j)%2==0){
                    pane.setStyle("-fx-background-color: #F0D9B5;");
                    pane.setOnMouseClicked(event -> {
                        if (game.getGameState().getPlayerTurn()!= Color.WHITE) return;
                        System.out.println("cliccato su cella "+ finalI+finalJ);
                        if (activePiece!=null && possibleMoveHighlight.contains(getLandingSquare(finalI,finalJ))){
                            boolean moved=game.getGameState().getBoard().move(
                                    currentPosition.getRow(),
                                    currentPosition.getColumn(),
                                    finalI,finalJ

                            );
                            System.out.println(game.getGameState().getBoard().transformIntoFen());
                            if (moved){
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();

                                activePiece=null;
                                currentPosition=null;

                                game.getGameState().setNextTurn();

                                drawPieces();
                                try {
                                    askEngineMove();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
                    cells[i][j]=pane;


                }else{
                    pane.setStyle("-fx-background-color: #B58863;");
                    pane.setOnMouseClicked(event -> {
                        if (game.getGameState().getPlayerTurn()!= Color.WHITE) return;
                        System.out.println("cliccato su cella "+ finalI+finalJ);
                        if (activePiece!=null && possibleMoveHighlight.contains(getLandingSquare(finalI,finalJ))){
                            boolean moved=game.getGameState().getBoard().move(
                                    currentPosition.getRow(),
                                    currentPosition.getColumn(),
                                    finalI,finalJ
                            );

                            if (moved){
                                resetSquareColor(possibleMoveHighlight);
                                possibleMoveHighlight.clear();

                                activePiece=null;
                                currentPosition=null;

                                game.getGameState().setNextTurn();
                                drawPieces();
                                try {
                                    askEngineMove();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
                    cells[i][j]=pane;

                }
                chessBoard.add(pane,i,j);
            }
        }
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

    public Position getLandingSquare(int i, int j){
        return new Position(i,j);
    }


    public void drawPossibleMove(List<Position> listPossibleMove){
        for (Position position :listPossibleMove){
            cells[position.getRow()][position.getColumn()].setStyle("-fx-background-color: #90eed8;");
        }
    }

    public void functionForClickOnPiece(ImageView imageView, Piece piece, int row, int col){
        imageView.setOnMouseClicked(event->{
            resetSquareColor(possibleMoveHighlight);
            possibleMoveHighlight.clear();
            if (game.getGameState().getPlayerTurn() != Color.WHITE) return;

            System.out.println("Clicked on " + piece.getPieceName() +  " "+ piece.getColor()+ " at " + row + "," + col);

            activePiece=piece;
            currentPosition=new Position(row,col);
            if (piece.getPieceName()==PieceName.PAWN) possibleMoveHighlight=game.getGameState().getPossibleMovePawn(piece,row,col);
            if (piece.getPieceName()==PieceName.ROOK) possibleMoveHighlight=game.getGameState().getPossibleMoveRook(piece,row,col);
            if (piece.getPieceName()==PieceName.KNIGHT) possibleMoveHighlight=game.getGameState().getPossibleMoveKnight(piece,row,col);
            if (piece.getPieceName()==PieceName.BISHOP) possibleMoveHighlight=game.getGameState().getPossibleMoveBishop(piece,row,col);
            if (piece.getPieceName()==PieceName.QUEEN) possibleMoveHighlight=game.getGameState().getPossibleMoveQueen(piece,row,col);
            drawPossibleMove(possibleMoveHighlight);
        });
    }

    // -97, c7c5
    public void transformResponse(String risposta){
        int startCol=risposta.charAt(0)-97;
        int startRow=Integer.parseInt(String.valueOf(risposta.charAt(1)));
        startRow--;
        int endCol=risposta.charAt(2)-97;
        int endRow=Integer.parseInt(String.valueOf(risposta.charAt(3)));
        endRow--;

        game.getGameState().getBoard().move(startRow,startCol,endRow,endCol);
        game.getGameState().setNextTurn();
        drawPieces();


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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);

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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);

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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);
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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);

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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);

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
                            functionForClickOnPiece(imageView,pezzo,finalI,finalJ);

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


    public void askEngineMove() throws IOException, InterruptedException {
        Gson gson=new Gson();
        String encodedFEN= URLEncoder.encode(game.getGameState().getBoard().transformIntoFen(), StandardCharsets.UTF_8);
        String URL="https://stockfish.online/api/s/v2.php?fen="+encodedFEN+"&depth=10";
        HttpClient httpClient=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL)).build();
        HttpResponse<String> response=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        Response rispostaClass=gson.fromJson(jsonString,Response.class);
        String best=rispostaClass.bestmove.split(" ")[1];
        transformResponse(best);

    }

}
