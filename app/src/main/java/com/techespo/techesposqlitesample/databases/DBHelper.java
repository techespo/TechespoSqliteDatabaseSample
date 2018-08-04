package com.techespo.techesposqlitesample.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "demo_db";
    private final static int DATABASE_VERSION = 1;
    private final static String USER_TBL = "user";

    private final String CREATE_USER_TABLE_QUERY= "Create table user (ID INTEGER PRIMARY KEY AUTOINCREMENT, name text,email text)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE_QUERY);
    }

    public SQLiteDatabase openDB(){
       return this.getWritableDatabase();
    }

    public void closeDB(){
         this.close();
    }

    public void insertMethod(ContentValues value){
        SQLiteDatabase db = openDB();
        db.insert(USER_TBL,null,value);
        closeDB();

    }

    public void updateMethod(ContentValues value,String id){
        String [] val =  {id};
        SQLiteDatabase db = openDB();
        db.update(USER_TBL,value,"id=?",val);
        closeDB();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
