package com.example.correctvoice;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "User";
    private static final String TABLE_Users = "Details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASS + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }

    void insertUserDetails(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_PASS, password);
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, email, password FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("password",cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            userList.add(user);
        }
        cursor.close();
        return  userList;
    }

    @SuppressLint("Range")
    public boolean search(String email , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT email, password FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            String emai = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
            String pass = cursor.getString(cursor.getColumnIndex(KEY_PASS));
            if(email.equals(emai) && password.equals(pass)){
                return true;
            }
        }
        cursor.close();
        return false;
    }

    @SuppressLint("Range")
    public boolean searchemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT email FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            String emai = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
            if(email.equals(emai)){
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public void DeleteUser(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

}
