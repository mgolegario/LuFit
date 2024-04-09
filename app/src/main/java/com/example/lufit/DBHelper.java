package com.example.lufit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    ArrayList<String> infos;

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, password TEXT, usuario TEXT, altura REAL, peso REAL, projeto TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password, String usuario, Float altura, Float peso, String projeto){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("usuario", usuario);
        contentValues.put("altura", altura);
        contentValues.put("peso", peso);
        contentValues.put("projeto", projeto);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }
    public Boolean updateData(String email, String senha, String usuario, Float altura, Float peso, String projeto){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("password", senha);
        contentValues.put("usuario", usuario);
        contentValues.put("altura", altura);
        contentValues.put("peso", peso);
        contentValues.put("projeto", projeto);

        long result = MyDB.update("users",  contentValues, "email=?", new String[]{email});
        if (result == -1) return false;
        else return true;
    }
    public Boolean updateDataSenha(String email, String senha){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", senha);
        long result = MyDB.update("users",  contentValues, "email=?", new String[]{email});
        if (result == -1) return false;
        else return true;
    }


    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount()>0)return true;
        else return false;
    }



    public ArrayList<Model> fetchData(String email){
    SQLiteDatabase MyDB = this.getReadableDatabase();
    Cursor cursor = MyDB.rawQuery("SELECT * FROM users where email = ?", new String[]{email});
    ArrayList<Model> arrOutrasInfos = new ArrayList<>();

        cursor.moveToNext();
        Model infos = new Model();
        infos.usuario = cursor.getString(2);
        infos.altura = cursor.getFloat(3);
        infos.peso = cursor.getFloat(4);
        infos.projeto = cursor.getString(5);

        arrOutrasInfos.add(infos);

        return arrOutrasInfos;

    }
}
