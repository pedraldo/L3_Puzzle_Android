package info.ups.fr.puzzlegame_template.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
