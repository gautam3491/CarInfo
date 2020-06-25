package com.example.carinfo;

import android.provider.BaseColumns;

public class CarCategoryContract implements BaseColumns {
    private CarCategoryContract(){}

    public static final class CarCategoryEntry implements BaseColumns {//provides id column for our table
        public static final String TABLE_NAME = "carcategoryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ID = "carid";
        public static final String COLUMN_TID = "cartid";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IMAGELARGE = "imagelarge";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_FAV = "fav";
    }
}
