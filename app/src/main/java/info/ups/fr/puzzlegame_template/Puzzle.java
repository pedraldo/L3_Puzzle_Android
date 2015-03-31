package info.ups.fr.puzzlegame_template;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by pierrepeinado on 24/03/15.
 */
public class Puzzle {
    
    private List<Piece> pieces;
    private int largeur_pieces;
    private int hauteur_pieces;
    private List<Piece> pieces_touched;
    
    public Puzzle(Bitmap bImage, int nbLignes, int nbColonnes, int largeurVue, int hauteurVue){
        int[] dimensions = new int[2];
        bImage = redimensionnerImage(bImage,largeurVue,hauteurVue);

        dimensions = decouperImage(bImage,nbLignes,nbColonnes, hauteurVue);
        this.largeur_pieces = dimensions[0];
        this.hauteur_pieces = dimensions[1];

        genererLiensPieces(nbLignes,nbColonnes);

        this.pieces = this.getShuffleListPiece();
        this.pieces_touched = new ArrayList<Piece>();
    }
    
    private Bitmap redimensionnerImage(Bitmap bImage, int largeur, int hauteur){
        Bitmap bResult = bImage;

        if(bImage.getWidth() > largeur){
            float coeffLargeur = (float)((float)largeur / (float)bImage.getWidth());
            bResult = Bitmap.createScaledBitmap(bImage, largeur, (int)((float)bImage.getHeight()*coeffLargeur), false);
        }

        if(bImage.getHeight() > hauteur){
            float coeffHauteur = (float)((float)hauteur / (float)bImage.getHeight());
            bResult = Bitmap.createScaledBitmap(bImage,(int)((float)bImage.getHeight()*coeffHauteur),hauteur,false);
        }

        return bResult;
    }


    private int[] decouperImage(Bitmap b, int nbLignes, int nbColonnes, int hauteurVue){
        int largeur = b.getWidth();
        int hauteur = b.getHeight();
        int largeur_piece = largeur/nbColonnes;
        int hauteur_piece = hauteur/nbLignes;

        this.pieces = new ArrayList<Piece>();

        int i,j;
        Piece p;
        final int const_deplacement_hauteur = (hauteurVue/2 - (nbLignes/2)*hauteur_piece);
        for(i=0; i<nbLignes;i++){
            for(j=0;j<nbColonnes;j++){
                int placement_y_piece = i*hauteur_piece + const_deplacement_hauteur;
                p = new Piece(Bitmap.createBitmap(b, j * largeur_piece, i*hauteur_piece, largeur_piece, hauteur_piece),j*largeur_piece,placement_y_piece);
                p.setX(j*largeur_piece);
                p.setY(placement_y_piece);
                this.pieces.add(p);
            }
        }

        int[] retour = {largeur_piece,hauteur_piece};

        return retour;
    }

    private void genererLiensPieces(int nbLignes, int nbColonnes){

        if(nbLignes == 1){
            for(int i=0;i<nbColonnes;i++){
                Piece tmp = pieces.get(i);
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,null);
                tmp.setCollapsedPiece(Piece.INDEX_BAS,null);

                if(i == 0){
                    tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,null);
                    tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(1));
                    continue;
                }

                if(i == nbColonnes - 1){
                    tmp.setCollapsedPiece(Piece.INDEX_DROITE,null);
                    tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(nbColonnes-2));
                    continue;
                }

                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(i-1));
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
            }

            return;
        }

        if(nbColonnes == 1){
            for(int i=0;i<nbLignes;i++){
                Piece tmp = pieces.get(i);
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,null);
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,null);

                if(i == 0){
                    tmp.setCollapsedPiece(Piece.INDEX_HAUT,null);
                    tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(1));
                    continue;
                }

                if(i == nbColonnes - 1){
                    tmp.setCollapsedPiece(Piece.INDEX_BAS,null);
                    tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(nbColonnes-2));
                    continue;
                }

                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-1));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+1));
            }

            return;
        }

        for(int i=0; i<(nbColonnes*nbLignes);i++){
            Piece tmp = pieces.get(i);

        //4 coins
            //Haut Gauche
            if(i==0){
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,null);
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,null);
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(1));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(nbColonnes));
                continue;
            }

            //Haut Droite
            if(i == nbColonnes-1){
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(nbColonnes-2));
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,null);
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,null);
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+nbColonnes));
                continue;
            }


            //Bas Droite
            if(i == (nbLignes*nbColonnes)-1){
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE, this.pieces.get((nbLignes*nbColonnes)-2));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,null);
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,null);
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
                continue;
            }

            //Bas Gauche
            if(i == nbColonnes*(nbLignes-1)){
                tmp.setCollapsedPiece(Piece.INDEX_BAS,null);
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,null);
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
                Log.d("test 4 coins Bas Gauche","index -> "+i);
                continue;
            }

        //4 côtés
            //Haut
            if(i>0 && i<nbColonnes-1){
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,null);
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(i-1));
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+nbColonnes));
                continue;
            }

            //Bas
            if(i>nbColonnes*(nbLignes-1) && i<(nbLignes*nbColonnes)-1){
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(i-1));
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,null);
                continue;
            }

            //Gauche
            if(i%nbColonnes == 0){
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,null);
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+nbColonnes));
                continue;
            }

            //Droite
            if(i%nbColonnes == nbColonnes-1){
                tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
                tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(i-1));
                tmp.setCollapsedPiece(Piece.INDEX_DROITE,null);
                tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+nbColonnes));
                continue;
            }
        //Cases entièrement entourées
            tmp.setCollapsedPiece(Piece.INDEX_HAUT,this.pieces.get(i-nbColonnes));
            tmp.setCollapsedPiece(Piece.INDEX_GAUCHE,this.pieces.get(i-1));
            tmp.setCollapsedPiece(Piece.INDEX_DROITE,this.pieces.get(i+1));
            tmp.setCollapsedPiece(Piece.INDEX_BAS,this.pieces.get(i+nbColonnes));
        }
    }

    private List<Piece> getShuffleListPiece(){
        List<Piece> shuffleList = this.pieces.subList(0,this.pieces.size());
        Collections.shuffle(shuffleList, new Random());

        return shuffleList;
    }

    public List<Piece> getListPiece(){
        return this.pieces;
    }

    public int getLargeurPieces(){
        return this.largeur_pieces;
    }

    public int getHauteurPieces(){
        return this.hauteur_pieces;
    }

    public void setPiecesTouched(int dX, int dY){
        for(Piece tmp:this.pieces_touched){
            tmp.setX(tmp.getX()+dX);
            tmp.setY(tmp.getY()+dY);
        }
    }

    public boolean setPieceTouched(int x, int y){
        int pos = this.numPieceTouched(x,y);
        if(pos != -1){
            this.pieces_touched.add(this.pieces.get(pos));
            this.pieces.add(this.pieces.remove(pos));
            this.completeListTouched(this.pieces_touched.get(0));
            return true;
        }
        return false;
    }

    private void completeListTouched(Piece p){
        for(int i=0; i<4;i++){
            if(p.getBool(i)){
                Piece tmp = p.getCollapsedPieces(i);
                if(tmp != null){
                    if(!this.pieces_touched.contains(tmp)){
                        this.pieces_touched.add(tmp);
                        this.completeListTouched(tmp);
                    }
                }
            }
        }
    }

    private int numPieceTouched(int x, int y){

        for(int i=this.pieces.size()-1; i>=0; i--){
            if(x >= this.pieces.get(i).getX() && x <= this.pieces.get(i).getX()+this.getLargeurPieces()
                    && y >= this.pieces.get(i).getY() && y <= this.pieces.get(i).getY()+this.getHauteurPieces()){
                return i;
            }
        }
        return -1;
    }

    public void doCollapsions(){
        int[] deplacements;
        for(Piece tmp:this.pieces_touched){
            deplacements = tmp.doCollapsion();
            if(deplacements[2] == 1){
                for(Piece p: this.pieces_touched){
                    p.setX(p.getX()+deplacements[0]);
                    p.setY(p.getY()+deplacements[1]);
                }
                break;
            }
        }
        this.testCollapsions();
    }

    private void testCollapsions(){
        for(Piece piece:this.pieces){
            piece.testCollapsions();
        }
    }

    public void clearPiecesTouched(){
        this.pieces_touched.clear();
    }

    public List<Piece> getListPiecesTouched(){
        return this.pieces_touched;
    }

    public boolean isPuzzleCompleted(){
        for(Piece piece:this.pieces){
            if(!piece.allCollapsedAreGood()){
                return false;
            }
        }
        return true;
    }

}
