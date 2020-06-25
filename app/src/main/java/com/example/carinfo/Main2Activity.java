package com.example.carinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {
    private TextView textView;
    private TextView textView1;
    private ImageView imageView;
    private Button sedan, hatchback, suv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        final String name = intent.getExtras().getString("name");
        final String id = Long.toString(intent.getExtras().getLong("cid"));


        textView = findViewById(R.id.item_id);
        textView.setText(name + " Options");

        sedan = findViewById(R.id.sedan);
        sedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CarCategoryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("tid", "1");
                intent.putExtra("name", name + " Sedan");

                startActivity(intent);
            }
        });

        hatchback = findViewById(R.id.hatchback);
        hatchback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CarCategoryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("tid", "2");
                intent.putExtra("name", name + " Hatchback");

                startActivity(intent);
            }
        });

        suv = findViewById(R.id.suv);
        suv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CarCategoryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("tid", "3");
                intent.putExtra("name", name + " SUV");

                startActivity(intent);
            }
        });

    }


}
