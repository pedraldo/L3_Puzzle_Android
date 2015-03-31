package info.ups.fr.puzzlegame_template;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class PuzzleActivity extends ActionBarActivity {

    private int imgRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        this.imgRes = getIntent().getIntExtra("imgRes",R.drawable.niveau1);

        TwoPiecesPuzzle puzzleView = (TwoPiecesPuzzle)this.findViewById(R.id.view);
        puzzleView.setImage(this.imgRes, getIntent().getIntExtra("nbLignes", 2), getIntent().getIntExtra("nbColonnes",2));

        Button bDisplayImage = (Button)this.findViewById(R.id.buttonDisplayImage);
        bDisplayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
            }
        });
    }

    private void showImage(){
        ImageView image = new ImageView(this);
        image.setImageResource(this.imgRes);
        new AlertDialog.Builder(this)
                .setTitle("Image complète")
                .setCancelable(true)
                .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(image)
                .create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
