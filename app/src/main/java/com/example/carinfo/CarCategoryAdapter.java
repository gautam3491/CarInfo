package com.example.carinfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarCategoryAdapter extends RecyclerView.Adapter<CarCategoryAdapter.CarCategoryViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public CarCategoryAdapter(Context context, Cursor cursor){//cursor to get data from database
        mContext = context;
        mCursor = cursor;

    }

    public class CarCategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public ImageView imageText;

        public CarCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            imageText = itemView.findViewById(R.id.imageview_image_item);
        }

    }

    @NonNull
    @Override
    public CarCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.car_item, parent, false);
        return new CarCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarCategoryViewHolder holder, final int position){
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry.COLUMN_NAME));
        int image = mCursor.getInt(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry.COLUMN_IMAGE));
        long id = mCursor.getLong(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry._ID));

        holder.nameText.setText(name);
        holder.imageText.setImageResource(image);
        holder.itemView.setTag(id);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, CarDetail.class);

                mCursor.moveToPosition(position);

                intent.putExtra("name", mCursor.getString(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry.COLUMN_NAME)));
                intent.putExtra("description", mCursor.getString(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry.COLUMN_DESCRIPTION)));
                intent.putExtra ("imagelarge", mCursor.getInt(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry.COLUMN_IMAGELARGE)));
                intent.putExtra("id", mCursor.getLong(mCursor.getColumnIndex(CarCategoryContract.CarCategoryEntry._ID)));

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //update new cursor
    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
