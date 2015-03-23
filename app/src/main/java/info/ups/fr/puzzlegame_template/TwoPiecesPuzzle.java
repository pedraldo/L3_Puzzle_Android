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


/**
 * TODO: document your custom view class.
 */
public class TwoPiecesPuzzle extends View {

    private int x1,y1,x2,y2;
    private int tX, tY;
    private int dX, dY;
    private int imageTouched;

    private Drawable mExampleDrawable;
    Bitmap myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece1);
    Bitmap myPict2 = BitmapFactory.decodeResource(getResources(), R.drawable.fishpiece2);


    public TwoPiecesPuzzle(Context context) {

        super(context);
        init();
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init(){
        this.x1 = this.getLeft();
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
                            Log.d("testDown1", x1 + " " + y1);
                        }

                        if(tX <= x2+myPict2.getWidth() && tX >= x2 && tY <= y2+myPict2.getHeight() && tY >= y2){
                            imageTouched = 2;
                            dX = x2 - tX;
                            dY = y2 - tY;
                            Log.d("testDown2", x1 + " " + y1);

                        }
                    break;

                    case MotionEvent.ACTION_MOVE:
                        Log.d("testMove", x1 + " " + y1);
                        switch (imageTouched){
                            case 1:
                                x1 = dX + (int)event.getX();
                                y1 = dY + (int)event.getY();
                                //Log.d("testMove", x1 + " " + y1);
                            break;

                            case 2:
                                x2 = dX + (int)event.getX();
                                y2 = dY + (int)event.getY();
                            break;
                        }

                        v.invalidate();
                    break;

                    case MotionEvent.ACTION_UP:

                    break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(myPict1, x1, y1, null);
        canvas.drawBitmap(myPict2, x2, y2, null);

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
