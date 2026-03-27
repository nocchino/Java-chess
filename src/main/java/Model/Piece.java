package Model;

import javafx.scene.image.Image;

import java.util.List;

public abstract class Piece {
     private PieceName pieceName;
     private Color color;
     private int value;
     private Image image;


     public Piece(PieceName name, Color color,int value){
         pieceName=name;
         this.color=color;
         this.value=value;
     }

     //GETTER---------------------------------------------
    public PieceName getPieceName() {
        return pieceName;
    }

    public Color getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }

    public int getValue() {
        return value;
    }
    //------------------------------------

    public boolean isInBound(int r, int c){
         return (r>=1&&r<=8)&&(c>=1&&c<=8);
    }

    public abstract List<Position> getpossibleMove(Board board,Position from);


}
