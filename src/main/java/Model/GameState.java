package Model;

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

    public Board getBoard() {
        return board;
    }
}
