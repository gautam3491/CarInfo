package com.example.carinfo;

import android.provider.BaseColumns;

public class CarContract {

    private CarContract(){}

    public static final class CarEntry implements BaseColumns {//provides id column for our table
        public static final String TABLE_NAME = "carList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
