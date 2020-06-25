package com.example.carinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CarDetail extends AppCompatActivity {

    private TextView textView, textView1;
    private String name, detail, cardetailid;
    private int image;
    private FloatingActionButton fab;
    private SQLiteDatabase mDatabase1;
    private ContentValues values;
    private ImageView imageView;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        CarCategoryDBHelper dbHelper1 = new CarCategoryDBHelper(this);
        mDatabase1 = dbHelper1.getWritableDatabase();



        final String[] selectionArgs = { "0" };

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        imageView = findViewById(R.id.imageView);
        fab = findViewById(R.id.floatingActionButton);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        detail = intent.getStringExtra("description");
        image = intent.getIntExtra("imagelarge", 1);
        cardetailid = Long.toString(intent.getExtras().getLong("id"));
        count = getAllCategoryItemsCount();
        values = new ContentValues();

        if (count > 0){
            values.put(CarCategoryContract.CarCategoryEntry.COLUMN_FAV, 0);
            fab.setImageResource(R.drawable.ic_remove);

        }else{
            values.put(CarCategoryContract.CarCategoryEntry.COLUMN_FAV, 1);
            fab.setImageResource(R.drawable.ic_add);
        }

        textView.setText(name);
        textView1.setText(detail);
        imageView.setImageResource(image);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase1.update(CarCategoryContract.CarCategoryEntry.TABLE_NAME, values, CarCategoryContract.CarCategoryEntry._ID + " = " + cardetailid, null);
                if (count > 0){
                    fab.setImageResource(R.drawable.ic_add);
                    Snackbar.make(view, "Remove From Favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    fab.setImageResource(R.drawable.ic_remove);
                    Snackbar.make(view, "Added To Favourite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }



    private int getAllCategoryItemsCount(){
        return mDatabase1.query(
                CarCategoryContract.CarCategoryEntry.TABLE_NAME,
                null,
                CarCategoryContract.CarCategoryEntry._ID + " = " + cardetailid + " and " + CarCategoryContract.CarCategoryEntry.COLUMN_FAV + " = 1",
                null,
                null,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " DESC"
        ).getCount();
    }

}
