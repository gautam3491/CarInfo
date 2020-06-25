package com.example.carinfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesFragment extends Fragment {
    private SQLiteDatabase mDatabase1;
    private CarCategoryAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        CarCategoryDBHelper dbHelper1 = new CarCategoryDBHelper(getContext());
        mDatabase1 = dbHelper1.getWritableDatabase();

        final RecyclerView recyclerView = rootView.findViewById(R.id.recycleviewsearch);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        mAdapter = new CarCategoryAdapter(getContext(), getAllCategoryItems());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }
    private Cursor getAllCategoryItems(){
        return mDatabase1.query(
                CarCategoryContract.CarCategoryEntry.TABLE_NAME,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_FAV + " =1 ",
                null,
                null,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
