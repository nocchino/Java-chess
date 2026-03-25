package Model;

public abstract class Piece {
     private PieceName pieceName;
     private Color color;
     private int value;


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


    public int getValue() {
        return value;
    }
    //------------------------------------

    public boolean isInBound(int r, int c){
         return (r>=1&&r<=8)&&(c>=1&&c<=8);
    }
}
