
package com.example.theelibrary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.theelibrary.DisplayActivity;
import com.example.theelibrary.MainPageActivity;

public class MyDbHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "Student.db";
    public static final String TABLE_NAME = "students_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PHONE";

    public MyDbHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PASSWORD TEXT, EMAIL TEXT, PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDetails(String name, String password, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, name);
        values.put(COL_3, password);
        values.put(COL_4, email);
        values.put(COL_5, phone);
        long res = db.insert(TABLE_NAME, null, values);
        if(res == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from students_table", null);
        return res;
    }
    public Cursor getOnlyOneUserData(String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from students_table where PASSWORD = "+"'"+ password +"'", null);
        return result;
    }

    public boolean updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET PASSWORD = "+"'"+password+"' " + "WHERE EMAIL = "+ "'" + email + "'");
        return true;
    }
}
