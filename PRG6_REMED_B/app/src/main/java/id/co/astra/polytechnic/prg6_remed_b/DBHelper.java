package id.co.astra.polytechnic.prg6_remed_b;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dosen.db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns
    private static final String TABLE_DOSEN = "dosen";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Create table SQL query
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_DOSEN + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOSEN);
        onCreate(db);
    }

    // Add new dosen
    public long addDosen(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long id = db.insert(TABLE_DOSEN, null, values);
        db.close();
        return id;
    }

    // Delete dosen
    public void deleteDosen(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOSEN, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Get all dosen
    public List<Dosen> getAllDosen() {
        List<Dosen> dosenList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOSEN, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_USERNAME, COLUMN_PASSWORD},
                null, null, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Dosen dosen = new Dosen();
                        dosen.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                        dosen.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                        dosen.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)));
                        dosen.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)));
                        dosenList.add(dosen);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return dosenList;
    }

    // Get dosen by ID
    public Dosen getDosen(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOSEN, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_USERNAME, COLUMN_PASSWORD},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        Dosen dosen = null;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    dosen = new Dosen();
                    dosen.setId(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    dosen.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                    dosen.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)));
                    dosen.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)));
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return dosen;
    }
}
