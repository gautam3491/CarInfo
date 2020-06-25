package com.example.carinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarCategoryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "carcategorylist.db";
    public static final int DATABASE_VERSION = 1;


    public CarCategoryDBHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CARCATEGORYLIST_TABLE = "CREATE TABLE " +
                CarCategoryContract.CarCategoryEntry.TABLE_NAME + " (" +
                CarCategoryContract.CarCategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_ID + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_TID + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_FAV + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_IMAGELARGE + " TEXT NOT NULL, " +
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                " );";

        db.execSQL(SQL_CREATE_CARCATEGORYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CarCategoryContract.CarCategoryEntry.TABLE_NAME);
        onCreate(db);
    }
}
