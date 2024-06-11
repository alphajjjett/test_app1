package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(location TEXT,date NUMBER,fromlo TEXT,name TEXT,address TEXT," +
                "number NUMBER primary key,information TEXT,moreinfo TEXT,request TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
    DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String location,String date,String fromlo,String name,String address,
                                String number,String information,String moreinfo,String request){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("date",date);
        contentValues.put("fromlo",fromlo);
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("number",number);
        contentValues.put("information",information);
        contentValues.put("moreinfo",moreinfo);
        contentValues.put("request",request);
        long result = DB.insert("Userdetails",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return  true;
        }
    }

    public Boolean updateuserdata(String location,String date,String fromlo,String name,String address,
                                  String number,String information,String moreinfo,String request){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("date",date);
        contentValues.put("fromlo",fromlo);
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("number",number);
        contentValues.put("information",information);
        contentValues.put("moreinfo",moreinfo);
        contentValues.put("request",request);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where number = ?",new String[]{number});
        if (cursor.getCount()>0) {
            long result = DB.update("Userdetails",contentValues, "number=?",new String[]{number});
            if(result == -1) {
                return false;
            }else {
                return true;
            }
        }else  {
            return false;
        }
    }

    public Boolean deletedata(String location,String date,String fromlo,String name,String address,
                                  String number,String information,String moreinfo,String request){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where number = ?",new String[]{number});
        if (cursor.getCount()>0) {
            long result = DB.delete("Userdetails", "number=?", new String[]{number});
            if (result==-1){
                return false;
            }else {
                return  true;
            }

        }else {
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null   );
        return  cursor;

    }



}
