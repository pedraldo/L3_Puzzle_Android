package info.ups.fr.puzzlegame_template;

/**
 * Created by Arnaud on 28/03/2015.
 */
public class Level {
    private int location;
    private int lvl;
    private Difficulte difficulte;
    private long time;
    private boolean ok;

    public Level(int location, int lvl, Difficulte difficulte){
        this.location = location;
        this.lvl = lvl;
        this.difficulte = difficulte;
        this.ok = false;
    }

    public String toString(){
        return "Niveau "+this.lvl;
    }

    public int nbLignes(){
        switch(this.difficulte){
            case FACILE:
                return 2;
            case MOYEN:
                return 3;
            case DIFFICILE:
                return 4;
            case EXPERT:
                return 6;
        }

        return 0;
    }

    public int nbColonnes(){
        switch(this.difficulte){
            case FACILE:
                return 2;
            case MOYEN:
                return 3;
            case DIFFICILE:
                return 4;
            case EXPERT:
                return 6;
        }

        return 0;
    }

    public int getBackgroundOnList(){
        switch(this.difficulte){
            case FACILE:
                return R.drawable.background_facile;
            case MOYEN:
                return R.drawable.background_moyen;
            case DIFFICILE:
                return R.drawable.background_difficile;
            case EXPERT:
                return R.drawable.background_expert;
        }

        return R.color.colorFacile;
    }

    public int getLevel(){
        return this.lvl;
    }

    public int getLocation(){
        return this.location;
    }

    public void setOk(boolean ok){
        this.ok = ok;
    }

    public void setTime(long time){
        this.time = time;
    }

    public boolean isOk(){
        return this.ok;
    }

    public long getTime(){
        return this.time;
    }
}
