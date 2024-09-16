package com.example.correctvoice.database;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.correctvoice.Model.Model;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bookmarkdb";
    private static final String TABLE_BOOKMARK = "bookmarked";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_BOOKMARK + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_IMAGE + " TEXT" +")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);
        // Create tables again
        onCreate(db);
    }

    public void insertNewsDetails(Model m) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        String title = m.getTitle();
        String description = m.getDescription();
        String image = m.getImage_url();
        cValues.put(KEY_TITLE, title);
        cValues.put(KEY_DESCRIPTION, description);
        cValues.put(KEY_IMAGE, image);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_BOOKMARK, null, cValues);
        db.close();
    }

    @SuppressLint("Range")
    public boolean findAndRemoveIfPresent(Model m){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id, title, description ,image FROM " + TABLE_BOOKMARK;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            if(m.getTitle().equals(title)){
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                DeleteUser(id);
                return true;
            }
        }
        return false;
    }

    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKMARK, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

    // Get User Details
    @SuppressLint("Range")
    public ArrayList<Model> GetAllNews() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Model> newsList = new ArrayList<>();
        String query = "SELECT title, description ,image FROM " + TABLE_BOOKMARK;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            String description =cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
            String image =cursor.getString(cursor.getColumnIndex(KEY_IMAGE));
            Model m = new Model(title , description , image);
            newsList.add(m);
        }
        return newsList;
    }
}
