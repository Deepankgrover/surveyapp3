package com.example.deepank.testapp;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    //COLUMNS
    static final String ROWID="id";
    static final String NAME="name";
    static final String POSITION = "position";

    static final String TAG = "DBAdapter";

    //DB PROPERTIES
    static final String DBNAME="g_DB";
    static final String TBNAME="g_TB";
    static final int DBVERSION='1';

    static final String CREATE_TB="CREATE TABLE g_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,position TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        // TODO Auto-generated constructor stub

        this.c=c;
        helper=new DBHelper(c);
    }

    // INNER HELPER DB CLASS
    private static class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try
            {
                db.execSQL(CREATE_TB);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

            Log.w(TAG, "upgrading DB");

            db.execSQL("DROP TABLE IF EXISTS g_TB");
            onCreate(db);

        }

    }

    // OPEN THE DB
    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;

    }

    //CLOSE THE DB
    public void close()
    {
        helper.close();
    }

    //INSERT INTO TABLE
    public long add(String name,String pos)
    {
        try
        {

            ContentValues cv=new ContentValues();
            cv.put(NAME, name);
            cv.put(POSITION, pos);

            return db.insert(TBNAME, ROWID, cv);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    //GET ALL VALUES
    public Cursor getAllValues()
    {
        String[] columns={ROWID,NAME,POSITION};

        return db.query(TBNAME, columns, null, null, null, null, null);
    }

}