package info.ups.fr.puzzlegame_template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


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

    private Drawable mExampleDrawable;

    private Puzzle puzzle;


    Bitmap myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
    Bitmap myPict2 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2);

    Bitmap oiseauBleu = BitmapFactory.decodeResource(getResources(), R.drawable.oiseaubleu);

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

    void init(){
        this.puzzle = new Puzzle(oiseauBleu,2,4,this.getWidth(),this.getHeight());


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                    break;

                    case MotionEvent.ACTION_MOVE:

                    break;

                    case MotionEvent.ACTION_UP:

                    break;
                }
                return true;
            }
        });



     /*   this.x1 = this.getLeft();
        this.y1 = this.getTop();
        this.x2 = this.getLeft() + this.getWidth()/3;
        this.y2 = this.getTop() + this.getHeight() + 20 + this.myPict2.getHeight();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tX = (int)event.getX();
                        tY = (int)event.getY();


                        if(tX <= x1+myPict1.getWidth() && tX >= x1 && tY <= y1+myPict1.getHeight() && tY >= y1){
                            imageTouched = 1;
                            dX = x1 - tX;
                            dY = y1 - tY;
                        }

                        if(tX <= x2+myPict2.getWidth() && tX >= x2 && tY <= y2+myPict2.getHeight() && tY >= y2){
                            imageTouched = 2;
                            dX = x2 - tX;
                            dY = y2 - tY;

                        }
                    break;

                    case MotionEvent.ACTION_MOVE:
                        switch (imageTouched){
                            case 1:
                                x1 = dX + (int)event.getX();
                                y1 = dY + (int)event.getY();

                            break;

                            case 2:
                                x2 = dX + (int)event.getX();
                                y2 = dY + (int)event.getY();

                            break;
                        }

                        v.invalidate();
                    break;

                    case MotionEvent.ACTION_UP:
                        if(x1 + myPict1.getWidth() - x2 <= 20 && x1 + myPict1.getWidth() - x2 >= -20 && y1 - y2 <= 20 && y1 - y2 >= -20){
                            switch(imageTouched){
                                case 1:
                                    x1 = x2 - myPict1.getWidth();
                                    y1 = y2;
                                break;

                                case 2:
                                    x2 = x1 + myPict1.getWidth();
                                    y2 = y1;
                                break;
                            }
                        }
                        v.invalidate();
                    break;
                }
                return true;
            }
        });
    */
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!first){
            this.init();
            this.invalidate();
            this.first = true;
        }

        List<Piece> shuffleListPiece = this.puzzle.getShuffleListPiece();
        Log.d("Test nb elmts","nb elements : "+shuffleListPiece.size());
        int largeur_piece = shuffleListPiece.get(0).getBitmap().getWidth();
        int hauteur_piece = shuffleListPiece.get(0).getBitmap().getHeight();
        int i;
        int x=0,y=0;

        for(i=0;i<shuffleListPiece.size();i++){
            canvas.drawBitmap(shuffleListPiece.get(i).getBitmap(), x, y, null);
            x += largeur_piece;
            if(x + largeur_piece > this.getWidth()){
                x=0;
                y+= hauteur_piece;
            }
        }

        // Draw the example drawable.
        if (mExampleDrawable != null) {
            mExampleDrawable.draw(canvas);
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
