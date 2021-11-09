package com.example.theelibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataIssue extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "issue.db";
    public static final String TABLE_NAME1 = "issue_table";
    public static final String C_1 = "id";
    public static final String C_2 = "libraryId";
    public static final String C_3 = "issueBook";
    public static final String C_4 = "issueDate";
    public static final String C_5 = "returnBook";
    public static final String C_6 = "returnDate";

    public DataIssue(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + TABLE_NAME1 + " (id INTEGER PRIMARY KEY AUTOINCREMENT, libraryId TEXT, issueBook TEXT, issueDate TEXT, returnBook TEXT, returnDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(database);
    }

    public boolean addIssueDetails(String libraryId1, String bookName, String bookDate){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_2, libraryId1);
        values.put(C_3, bookName);
        values.put(C_4, bookDate);
        values.put(C_5, "Nothing");
        values.put(C_6, "00/00/0000");
        long res = database.insert(TABLE_NAME1, null, values);
        if(res == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateIssueDetails(String id, String s1 , String s2) {
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("UPDATE "+TABLE_NAME1+" SET issueBook = "+"'"+s1+"', issueDate = "+"'"+s2+"' "+ "WHERE libraryId = "+"'"+id+"'");
        return true;
    }

    public Cursor getLastIssueDate(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor date = database.rawQuery("SELECT * FROM issue_table WHERE id = (SELECT max(id) FROM issue_table)", null);
        return date;
    }

    public boolean updateReturnBookDetails(String id1, String st1 , String st2) {
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("UPDATE "+TABLE_NAME1+" SET returnBook = "+"'"+st1+"', returnDate = "+"'"+st2+"' "+ "WHERE libraryId = "+"'"+id1+"'");
        return true;
    }
}
