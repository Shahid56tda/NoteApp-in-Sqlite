package com.example.noteappsqlit;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Calendar;

public class DatabaserHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME="notes.db";
        public static final String TABLE_NAME="notes_table";
        public static final String COL_1="ID";

        public static final String COL_2="Title";
        public static final String COL_3="CONTENT";
        public  static final String COL_4="TIME";
    Calendar calendar=Calendar.getInstance();
    String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());




    public DatabaserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT, CONTENT TEXT,TIME TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String title,String content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        // contentValues.put(COL_1,id);

        contentValues.put(COL_2,title);
        contentValues.put(COL_3,content);
        contentValues.put(COL_4,currentDate);
        long success=db.insert(TABLE_NAME,null,contentValues);
        if(success==-1)
        {
            return false;
        }
        else {

            return  true;

        }
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur =db.rawQuery("select * from "+TABLE_NAME,null);
        return cur;
    }
    public boolean UpdateData(String id,String title,String content)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,content);
        contentValues.put(COL_4,currentDate);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID=?",new String[]{id});

    }
}
