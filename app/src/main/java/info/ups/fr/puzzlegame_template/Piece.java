package info.ups.fr.puzzlegame_template;

import android.graphics.Bitmap;
import android.util.Log;

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

    public Piece(Bitmap bImage, int x, int y){
        this.tBool = new boolean[]{false, false, false, false};

        this.tPiece = new Piece[4];

        this.bImage = bImage;
        this.x = x;
        this.y = y;
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

    public void setCollapsion(int pos){
        this.tBool[pos] = true;
    }

    public void doCollapsion(){
        if(this.tPiece[INDEX_HAUT] != null){
            if(Math.abs(this.x - this.tPiece[INDEX_HAUT].getX()) <= 20 && Math.abs(this.y - (this.tPiece[INDEX_HAUT].getY()+this.bImage.getHeight())) <= 20){
                this.tBool[INDEX_HAUT] = true;
                this.tPiece[INDEX_HAUT].setCollapsion(INDEX_BAS);
                this.x = this.tPiece[INDEX_HAUT].getX();
                this.y = this.tPiece[INDEX_HAUT].getY()+this.bImage.getHeight();
                return;
            }
            else{
                this.tBool[INDEX_HAUT] = false;
            }
        }

        if(this.tPiece[INDEX_GAUCHE] != null){
            if(Math.abs(this.x - (this.tPiece[INDEX_GAUCHE].getX()+this.bImage.getWidth())) <= 20 && Math.abs(this.y - this.tPiece[INDEX_GAUCHE].getY()) <= 20){
                this.tBool[INDEX_GAUCHE] = true;
                this.tPiece[INDEX_GAUCHE].setCollapsion(INDEX_DROITE);
                this.x = this.tPiece[INDEX_GAUCHE].getX()+this.bImage.getWidth();
                this.y = this.tPiece[INDEX_GAUCHE].getY();
                return;
            }
            else{
                this.tBool[INDEX_GAUCHE] = false;
            }

        }

        if(this.tPiece[INDEX_BAS] != null){
            Log.d("test bas","absX: "+Math.abs(this.x - this.tPiece[INDEX_BAS].getX())+"    absY: "+Math.abs(this.y+this.bImage.getHeight() - this.tPiece[INDEX_BAS].getY()));
            if(Math.abs(this.x - this.tPiece[INDEX_BAS].getX()) <= 20 && Math.abs(this.y+this.bImage.getHeight() - this.tPiece[INDEX_BAS].getY()) <= 20){
                this.tBool[INDEX_BAS] = true;
                this.tPiece[INDEX_BAS].setCollapsion(INDEX_HAUT);
                this.x = this.tPiece[INDEX_BAS].getX();
                this.y = this.tPiece[INDEX_BAS].getY()-this.bImage.getHeight();
                return;
            }
            else{
                this.tBool[INDEX_BAS] = false;
            }
        }

        if(this.tPiece[INDEX_DROITE] != null){
            if(Math.abs(this.x - this.tPiece[INDEX_DROITE].getX() + this.bImage.getWidth()) <= 20 && Math.abs(this.y - this.tPiece[INDEX_DROITE].getY()) <= 20){
                this.tBool[INDEX_DROITE] = true;
                this.tPiece[INDEX_DROITE].setCollapsion(INDEX_GAUCHE);
                this.x = this.tPiece[INDEX_DROITE].getX()-this.bImage.getWidth();
                this.y = this.tPiece[INDEX_DROITE].getY();
                return;
            }
            else{
                this.tBool[INDEX_DROITE] = false;
            }
        }
    }
}
