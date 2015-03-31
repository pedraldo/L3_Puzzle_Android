package info.ups.fr.puzzlegame_template.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

    public static final String PUZZLE_TABLE_NAME = "PUZZLE";
    public static final String PUZZLE_LEVEL = "LEVEL";
    public static final String PUZZLE_OK = "OK";
    public static final String PUZZLE_TIME = "TIME";

    public static final String PUZZLE_TABLE_CREATE = "CREATE TABLE " + PUZZLE_TABLE_NAME + " (" +
                                                   PUZZLE_LEVEL + " INTEGER PRIMARY KEY, " +
                                                   PUZZLE_OK + " BOOLEAN, " +
                                                   PUZZLE_TIME + " REAL"
                                                   + ");";

    public static final String PUZZLE_TABLE_DROP = "DROP TABLE IF EXISTS " + PUZZLE_TABLE_NAME + ";";

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PUZZLE_TABLE_CREATE);
        this.insertLevel(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PUZZLE_TABLE_DROP);
        db.execSQL(PUZZLE_TABLE_CREATE);
        this.insertLevel(db);
    }

    private void insertLevel(SQLiteDatabase db){
        String query;

        for(int i = 1; i < 13; i++){
            query = "INSERT INTO " + PUZZLE_TABLE_NAME + " (" +
                    PUZZLE_LEVEL + ", " +
                    PUZZLE_OK + ") VALUES (" +
                    i + ", " +
                    "0"
                    + ");";

            db.execSQL(query);
        }
    }
}