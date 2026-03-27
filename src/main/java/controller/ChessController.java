package controller;

import Model.Board;
import Model.Game;
import Model.Piece;
import Model.Position;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class ChessController {
    private Game game;
    private Board board;
    private Pane[][] cells = new Pane[8][8];

    @FXML private Pane cell00,cell01,cell02,cell03;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(){
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void showPossibleMove(Piece piece, Position position){
        System.out.println("ho cliccato");
    }

    public void setGame(Game game){
        this.game=game;
    }

    public void showPossibleMove(){

    }
    public void showPosition(){

    }


    public void initialize() {
        cells[0][0] = cell00;
        cells[0][1] = cell01;
        cells[0][2] = cell02;
        cells[0][3] = cell03;

        // ... e così via per tutte le celle
    }

    // Nel controller
    public void updateBoard() {
        Board board = game.getGameState().getBoard();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPieceAt(r, c);
                Pane cell=cells[r][c];
                if (p != null) {
                    // Aggiorna la cella con l'immagine del pezzo
                    // Ad esempio, potresti avere un metodo getImage() in Piece che restituisce l'immagine del pezzo
                    // cell.setBackground(new Background(new BackgroundImage(p.getImage(), ...)));
                    cell.setBackground(new Background(new javafx.scene.layout.BackgroundImage(p.getImage(), null, null, null, null)));
                } else {
                    // Rimuovi l'immagine se non c'è un pezzo
                    // cell.setBackground(null);
                }

            }
        }
    }

}
