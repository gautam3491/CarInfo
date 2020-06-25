package com.example.carinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private SQLiteDatabase mDatabase1;
    private CarAdapter mAdapter;
    private Button button;
    private EditText edittext;
    List<Car> car;
    List<CarCategory> carcategory;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        CarDBHelper dbHelper = new CarDBHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();

        CarCategoryDBHelper dbHelper1 = new CarCategoryDBHelper(getContext());
        mDatabase1 = dbHelper1.getWritableDatabase();

        addItem();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        mAdapter = new CarAdapter(getContext(), getAllItems());

        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
    private void addItem(){
        car = new ArrayList<>();
        car.add(new Car("Ford", R.drawable.ford));
        car.add(new Car("Nissan", R.drawable.nissan));
        car.add(new Car("Mazda", R.drawable.mazda));

        if (getAllItemsCount() == 0) {
            for (Car c : car) {
                ContentValues cv = new ContentValues();
                cv.put(CarContract.CarEntry.COLUMN_NAME, c.getTitle());
                cv.put(CarContract.CarEntry.COLUMN_IMAGE, c.getThumbnail());
                mDatabase.insert(CarContract.CarEntry.TABLE_NAME, null, cv);
            }
        }

        carcategory = new ArrayList<>();

        carcategory.add(new CarCategory("Fiesta", 1,1, "Ford's long running subcompact line based on global B-car Platform.", R.drawable.fiesta, R.drawable.fiesta_large, 0));
        carcategory.add(new CarCategory("Focus", 1,2, "Ford's Compact car based on global C-car platform.", R.drawable.focus, R.drawable.focus_large, 0));
        carcategory.add(new CarCategory("Ranger", 1,3, "Tuned version of Ford's mid sized pickup truck.", R.drawable.ranger, R.drawable.ranger_large, 0));

        carcategory.add(new CarCategory("Altima", 2,1, "The name 'Altima' was originally applied to a top trim line of the Nissan Leopard for the Japanese market in 1986.", R.drawable.altima, R.drawable.altima_large, 0));
        carcategory.add(new CarCategory("Juke", 2,2, "The Juke was designed at Nissan Design Europe in London and refined at Nissan's Design Centre in Japan.", R.drawable.juke, R.drawable.juke_large, 0));
        carcategory.add(new CarCategory("Teana", 2,3, "The name 'Teana' is from that of a small village in Italy, sharing a naming influence for the Nissan Murano, another city in Italy, which appeared at the same time.", R.drawable.teana, R.drawable.teana_large, 0));

        carcategory.add(new CarCategory("Atenza", 3,1, "The Mazda 6 or Mazda6 (known as the Mazda Atenza in Japan and China, derived from the Italian attenzione) is a mid-size car produced by Mazda since 2002", R.drawable.atenza, R.drawable.atenza_large, 0));
        carcategory.add(new CarCategory("Demio", 3,2, "The Mazda Demio is a supermini manufactured and marketed globally by Mazda since 1996, currently in its fourth generation.", R.drawable.demio, R.drawable.demio_large, 0));
        carcategory.add(new CarCategory("Axela", 3,3, "The Mazda 3 (known as the Mazda Axela in Japan (first three generations), a combination of 'accelerate' and 'excellent') is a compact car manufactured in Japan by Mazda.", R.drawable.axela, R.drawable.azela_large, 0));

        if (getAllCategoryItemsCount() == 0) {
            for (CarCategory c : carcategory) {
                ContentValues cv = new ContentValues();
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_NAME, c.getTitle());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_ID, c.getCategory());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_TID, c.getType());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_DESCRIPTION, c.getDescription());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_IMAGE, c.getThumbnail());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_IMAGELARGE, c.getThumbnailLarge());
                cv.put(CarCategoryContract.CarCategoryEntry.COLUMN_FAV, c.getFav());
                mDatabase1.insert(CarCategoryContract.CarCategoryEntry.TABLE_NAME, null, cv);

            }
        }

    }

    private Cursor getAllItems(){
        //like select query
        return mDatabase.query(
                CarContract.CarEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CarContract.CarEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }

    private int getAllItemsCount(){
        return mDatabase.query(
                CarContract.CarEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CarContract.CarEntry.COLUMN_TIMESTAMP + " DESC"
        ).getCount();
    }

    private int getAllCategoryItemsCount(){
        return mDatabase1.query(
                CarCategoryContract.CarCategoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " DESC"
        ).getCount();
    }



}
