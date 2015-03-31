package info.ups.fr.puzzlegame_template;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import info.ups.fr.puzzlegame_template.BDD.BDDTools;


/**
 * TODO: document your custom view class.
 */
public class TwoPiecesPuzzle extends View {

    private int x1,y1,x2,y2;
    private int fix1,fix2;
    private int tX, tY;
    private int dX, dY;
    private int imageTouched;
    private boolean first = false;
    private boolean isSet = false;
    private boolean isPieceTouched;
    private int nbLignes;
    private int nbColonnes;
    private Bitmap img;
    private int lvl;
    private long startTime;
    private long endTime;
    private int numPiece;

    private Drawable mExampleDrawable;

    private Puzzle puzzle;

	
	private int test;

    Bitmap oiseauBleu = BitmapFactory.decodeResource(getResources(), R.drawable.niveau1);

    List<Bitmap> ListBitmap;
    List<Bitmap> ListBitmapShuffle;


    public TwoPiecesPuzzle(Context context) {

        super(context);
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImage(int lvl, int resImg, int nbLignes, int nbColonnes){
        if(!this.isSet){
            this.startTime = System.currentTimeMillis();
            this.lvl = lvl;
            this.nbLignes = nbLignes;
            this.nbColonnes = nbColonnes;
            this.img = BitmapFactory.decodeResource(getResources(), resImg);
            this.isSet = true;
            this.invalidate();
        }
    }

    void init(){
        this.isPieceTouched = false;
        this.puzzle = new Puzzle(this.img,this.nbLignes,this.nbColonnes,this.getWidth(),this.getHeight());

        int x,y;
        List<Piece> ListPiece = this.puzzle.getListPiece();
        int largeur_piece = ListPiece.get(0).getBitmap().getWidth();
        int hauteur_piece = ListPiece.get(0).getBitmap().getHeight();
        int i;

        x=(int) ((float) (this.getWidth() / 2) - ((float) ((float)nbColonnes / 2) * puzzle.getLargeurPieces()));
        y=(int) ((float) (this.getHeight()/2) - ((float) ((float)nbLignes/2)*puzzle.getHauteurPieces()));

        for(i=0;i<ListPiece.size();i++){
            ListPiece.get(i).setX(x);
            ListPiece.get(i).setY(y);
            x += largeur_piece;
            if(x+largeur_piece > this.getWidth() || i+1%nbColonnes == 0){
                x=(int) ((float) (this.getWidth() / 2) - ((float) ((float)nbColonnes / 2) * puzzle.getLargeurPieces()));
                y+= hauteur_piece;
            }
        }

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tX = (int)event.getX();
                        tY = (int)event.getY();
                        isPieceTouched = puzzle.setPieceTouched(tX,tY);

                        if(isPieceTouched){
                            //dX = puzzle.getPieceTouched().getX() - tX;
                            //dY = puzzle.getPieceTouched().getY() - tY;
                        }
                    break;

                    case MotionEvent.ACTION_MOVE:

                        if(isPieceTouched && isPiecesTouchedIn((int) event.getX() - tX, (int) event.getY() - tY)){
                            puzzle.setPiecesTouched((int)event.getX()-tX,(int)event.getY()-tY);
                            tX = (int)event.getX();
                            tY = (int)event.getY();
                            v.invalidate();
                        }

                    break;

                    case MotionEvent.ACTION_UP:
                        if(isPieceTouched){
                            puzzle.doCollapsions();
                            puzzle.clearPiecesTouched();
                            isPieceTouched = false;
                            v.invalidate();

                            if(puzzle.isPuzzleCompleted()){
                                puzzleFinish();
                            }
                        }
                    break;
                }
                return true;
            }
        });
    }

    private boolean isPiecesTouchedIn(int dX, int dY){
        for(Piece tmp:puzzle.getListPiecesTouched()){
            if(tmp.getX()+dX+tmp.getBitmap().getWidth() > this.getWidth()+tmp.getBitmap().getWidth()/2 || tmp.getX()+dX < -tmp.getBitmap().getWidth()/2 || tmp.getY()+dY+tmp.getBitmap().getHeight() > this.getHeight()+tmp.getBitmap().getHeight()/2 || tmp.getY()+dY < -tmp.getBitmap().getHeight()/2){
                return false;
            }
        }
        return true;
    }

    private void puzzleFinish(){
        this.endTime = System.currentTimeMillis();
        BDDTools bddHandler = new BDDTools(this.getContext());

        if(bddHandler.getLevelOK(this.lvl)){
            if(bddHandler.getLevelTime(this.lvl) > this.endTime-this.startTime)
                bddHandler.setLevelTime(this.lvl, this.endTime-this.startTime);
        }else{
            bddHandler.setLevelOK(this.lvl, true);
            bddHandler.setLevelTime(this.lvl, this.endTime-this.startTime);
        }

        this.launchDialogFinish();
    }

    private void launchDialogFinish(){
        new AlertDialog.Builder(this.getContext())
                .setTitle("Partie finie")
                .setMessage("Bravo ! Vous avez réussi à assembler le puzzle !")
                .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        getContext().startActivity(intent);
                    }
                })
                .setIcon(R.drawable.vvert)
                .show();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(this.isSet) {
            if (!first) {
                this.init();
                this.invalidate();
                this.first = true;
            }


            for (Piece tmp : this.puzzle.getListPiece()) {
                canvas.drawBitmap(tmp.getBitmap(), tmp.getX(), tmp.getY(), null);
            }

            // Draw the example drawable.
            if (mExampleDrawable != null) {
                mExampleDrawable.draw(canvas);
            }
        }
    }




    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
