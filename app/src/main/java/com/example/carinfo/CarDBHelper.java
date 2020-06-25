package com.example.carinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.carinfo.CarContract.*;

public class CarDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "carlist.db";
    public static final int DATABASE_VERSION = 1;


    public CarDBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CARLIST_TABLE = "CREATE TABLE " +
                CarEntry.TABLE_NAME + " (" +
                CarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CarEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CarEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
                CarEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                " );";

        db.execSQL(SQL_CREATE_CARLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CarEntry.TABLE_NAME);
        onCreate(db);
    }
}
