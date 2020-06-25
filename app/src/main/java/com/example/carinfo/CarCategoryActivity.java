package com.example.carinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CarCategoryActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private CarCategoryAdapter mAdapter;
    TextView textView;
    String id, tid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_category);

        textView = findViewById(R.id.item_id);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tid = intent.getStringExtra("tid");
        name = intent.getStringExtra("name");

        textView.setText(name);

        CarCategoryDBHelper dbHelper1 = new CarCategoryDBHelper(this);

        mDatabase = dbHelper1.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerviewcategory);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mAdapter = new CarCategoryAdapter(this, getAllItems());

        recyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllItems(){
        //like select query
        return mDatabase.query(
                CarCategoryContract.CarCategoryEntry.TABLE_NAME,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_ID + " = " + id + " AND " + CarCategoryContract.CarCategoryEntry.COLUMN_TID + " = " + tid,
                null,
                null,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


}
