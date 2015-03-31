package info.ups.fr.puzzlegame_template.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Arnaud on 31/03/2015.
 */
public class BDDTools extends DAODataBase {

    public BDDTools(Context context){
        super(context);
    }

    public void setLevelOK(int lvl, boolean ok){
        this.open();

        String bool;
        if(ok)
            bool = "1";
        else
            bool = "0";

        ContentValues value = new ContentValues();
        value.put(DataBaseHandler.PUZZLE_OK, bool);

        mDb.update(DataBaseHandler.PUZZLE_TABLE_NAME, value, DataBaseHandler.PUZZLE_LEVEL + " = ?", new String[]{String.valueOf(lvl)});

        this.close();
    }

    public void setLevelTime(int lvl, long time){
        this.open();

        ContentValues value = new ContentValues();
        value.put(DataBaseHandler.PUZZLE_TIME, String.valueOf(time));

        mDb.update(DataBaseHandler.PUZZLE_TABLE_NAME, value, DataBaseHandler.PUZZLE_LEVEL + " = ?", new String[]{String.valueOf(lvl)});

        this.close();
    }

    public boolean getLevelOK(int lvl){
        this.open();

        Cursor c = mDb.rawQuery("select "+DataBaseHandler.PUZZLE_OK+" from "+DataBaseHandler.PUZZLE_TABLE_NAME+
                " where "+DataBaseHandler.PUZZLE_LEVEL+" = ?;", new String[]{String.valueOf(lvl)});

        c.moveToFirst();
        int value = c.getInt(0);
        c.close();
        this.close();

        return value == 1;
    }

    public long getLevelTime(int lvl){
        this.open();

        Cursor c = mDb.rawQuery("select "+DataBaseHandler.PUZZLE_TIME+" from "+DataBaseHandler.PUZZLE_TABLE_NAME+
                                " where "+DataBaseHandler.PUZZLE_LEVEL+" = ?;", new String[]{String.valueOf(lvl)});

        c.moveToFirst();
        long value = c.getLong(0);
        c.close();
        this.close();

        return value;
    }
}
