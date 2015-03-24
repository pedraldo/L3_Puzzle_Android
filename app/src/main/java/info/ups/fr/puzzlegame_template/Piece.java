package info.ups.fr.puzzlegame_template;

import android.graphics.Bitmap;

/**
 * Created by pierrepeinado on 24/03/15.
 */
public class Piece {
    public final static int INDEX_HAUT = 0;
    public final static int INDEX_DROITE = 1;
    public final static int INDEX_BAS = 2;
    public final static int INDEX_GAUCHE = 3;

    private Bitmap bImage;
    private int x,y;
    private boolean tBool[];
    private Piece tPiece[];

    public Piece(Bitmap bImage){
        this.tBool = new boolean[]{false, false, false, false};

        this.tPiece = new Piece[4];

        this.bImage = bImage;
        this.x = 0;
        this.y = 0;
    }

    public Bitmap getBitmap(){
        return this.bImage;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public boolean getBool(int pos){
        return this.tBool[pos];
    }

    public Piece getCollapsedPieces(int pos){
        return this.tPiece[pos];
    }

    public void setCollapsedPiece(int pos, Piece p){
        if(p == null){
            this.tBool[pos] = true;
            this.tPiece[pos] = null;
        }
        else{
            this.tPiece[pos] = p;
        }
    }

    public boolean allCollapsedAreGood(){
        for(int i=0;i<4;i++){
            if(this.tBool[i] == false) return false;
        }

        return true;
    }
}
