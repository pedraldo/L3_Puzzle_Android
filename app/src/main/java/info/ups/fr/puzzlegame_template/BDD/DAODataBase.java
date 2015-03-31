package info.ups.fr.puzzlegame_template.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Arnaud on 31/03/2015.
 */
public abstract class DAODataBase {
    protected final static int VERSION = 1;

    protected final static String NOM = "databasePuzzle.db";

    protected SQLiteDatabase mDb = null;
    protected DataBaseHandler mHandler = null;

    public DAODataBase(Context pContext) {
        this.mHandler = new DataBaseHandler(pContext, NOM, null, VERSION);
    }

    protected void open() {
        mDb = mHandler.getWritableDatabase();
    }

    protected void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

    public void reinit(){
        this.open();
        this.mDb.execSQL(DataBaseHandler.PUZZLE_TABLE_DROP);
        this.mDb.execSQL(DataBaseHandler.PUZZLE_TABLE_CREATE);
        DataBaseHandler.insertLevel(this.mDb);
        this.close();
    }
}
