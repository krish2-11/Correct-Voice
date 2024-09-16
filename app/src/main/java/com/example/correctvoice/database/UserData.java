package com.example.correctvoice.database;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.correctvoice.Model.Model;

import java.util.ArrayList;

public class UserData extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userdb";
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";

    public UserData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_GENDER + " TEXT"+")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    public void insertUserDetails(String name , String phone , String email ,String gender) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PHONE, phone);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_GENDER, gender);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_USER, null, cValues);
        db.close();
    }
}
