package com.example.loginandsignupusingsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
         mydb.execSQL("create table logintable(email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldversion, int newversion) {
        mydb.execSQL("drop table if exists logintable");
    }

    public boolean insertData(String email,String password){
        SQLiteDatabase mydb = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = mydb.insert("logintable",null,contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkemail(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from logintable where email = ?",new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean checkemailpassword(String email,String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from logintable where email = ? and password= ?",new String[] {email,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

}
