package com.example.carinfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {
    SearchView searchViews;
    private SQLiteDatabase mDatabase1;
    private CarCategoryAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        CarCategoryDBHelper dbHelper1 = new CarCategoryDBHelper(getContext());
        mDatabase1 = dbHelper1.getWritableDatabase();
        searchViews = rootView.findViewById(R.id.searchCar);

        final RecyclerView recyclerView = rootView.findViewById(R.id.recycleviewsearch);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));






        searchViews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
//                getAllCategoryItems(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                if (!newText.isEmpty()) {
                    mAdapter = new CarCategoryAdapter(getContext(), getAllCategoryItems(newText));
                    recyclerView.setAdapter(mAdapter);

                }else{
                    recyclerView.setAdapter(null);
                }
                return false;
            }
        });

        return rootView;
    }

    private Cursor getAllCategoryItems(String query){
        return mDatabase1.query(
                CarCategoryContract.CarCategoryEntry.TABLE_NAME,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_NAME + " like '" + query + "%'",
                null,
                null,
                null,
                CarCategoryContract.CarCategoryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
