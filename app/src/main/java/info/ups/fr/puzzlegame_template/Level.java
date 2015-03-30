package info.ups.fr.puzzlegame_template;

/**
 * Created by Arnaud on 28/03/2015.
 */
public class Level {
    private int location;
    private int lvl;
    private Difficulte difficulte;

    public Level(int location, int lvl, Difficulte difficulte){
        this.location = location;
        this.lvl = lvl;
        this.difficulte = difficulte;
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

    public int getLocation(){
        return this.location;
    }
}
