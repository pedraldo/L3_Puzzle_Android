package info.ups.fr.puzzlegame_template;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.ups.fr.puzzlegame_template.BDD.BDDTools;


public class MainActivity extends ActionBarActivity {

    private ListView listviewLevel;
    private List<Level> listLevel = new ArrayList<Level>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.initLevel();
        this.initListView();
    }

    private void initLevel(){
        this.listLevel.add(new Level(R.drawable.niveau1,1,Difficulte.FACILE));
        this.listLevel.add(new Level(R.drawable.niveau2,2,Difficulte.FACILE));
        this.listLevel.add(new Level(R.drawable.niveau3,3,Difficulte.FACILE));
        this.listLevel.add(new Level(R.drawable.niveau4,4,Difficulte.MOYEN));
        this.listLevel.add(new Level(R.drawable.niveau5,5,Difficulte.MOYEN));
        this.listLevel.add(new Level(R.drawable.niveau6,6,Difficulte.MOYEN));
        this.listLevel.add(new Level(R.drawable.niveau7,7,Difficulte.DIFFICILE));
        this.listLevel.add(new Level(R.drawable.niveau8,8,Difficulte.DIFFICILE));
        this.listLevel.add(new Level(R.drawable.niveau9,9,Difficulte.DIFFICILE));
        this.listLevel.add(new Level(R.drawable.niveau10,10,Difficulte.EXPERT));
        this.listLevel.add(new Level(R.drawable.niveau11,11,Difficulte.EXPERT));
        this.listLevel.add(new Level(R.drawable.niveau12,12,Difficulte.EXPERT));

        BDDTools bddHandler = new BDDTools(this);
        for(int i = 0; i < 12; i++){
            this.listLevel.get(i).setOk(bddHandler.getLevelOK(i+1));
            this.listLevel.get(i).setTime(bddHandler.getLevelTime(i + 1));
        }
    }

    private void initListView(){
        this.listviewLevel = (ListView) this.findViewById(R.id.listeLevel);
        Level[] values= new Level[this.listLevel.size()];
        for(int i = 0; i < this.listLevel.size(); i++){
            values[i] = this.listLevel.get(i);
        }
        ListLevelAdapter adapter = new ListLevelAdapter(this, R.layout.list_level_row, values);
        this.listviewLevel.setAdapter(adapter);

        this.listviewLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0 || listLevel.get(position-1).isOk()){
                    Intent intent = new Intent(getBaseContext(), PuzzleActivity.class);
                    intent.putExtra("lvl", listLevel.get(position).getLevel());
                    intent.putExtra("imgRes", listLevel.get(position).getLocation());
                    intent.putExtra("nbLignes", listLevel.get(position).nbLignes());
                    intent.putExtra("nbColonnes", listLevel.get(position).nbColonnes());
                    startActivity(intent);
                }
            }
        });
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
        if (id == R.id.action_reinit) {
            BDDTools bddHandler = new BDDTools(this);
            bddHandler.reinit();
            this.initLevel();
            this.initListView();
        }

        return super.onOptionsItemSelected(item);
    }
}
