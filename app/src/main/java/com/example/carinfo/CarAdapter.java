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

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public CarAdapter(Context context, Cursor cursor){//cursor to get data from database
        mContext = context;
        mCursor = cursor;

    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public ImageView imageText;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            imageText = itemView.findViewById(R.id.imageview_image_item);
        }

    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, final int position){
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(CarContract.CarEntry.COLUMN_NAME));
        int image = mCursor.getInt(mCursor.getColumnIndex(CarContract.CarEntry.COLUMN_IMAGE));
        long id = mCursor.getLong(mCursor.getColumnIndex(CarContract.CarEntry._ID));

        holder.nameText.setText(name);
        holder.imageText.setImageResource(image);
        holder.itemView.setTag(id);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, Main2Activity.class);

                mCursor.moveToPosition(position);

                intent.putExtra("name", mCursor.getString(mCursor.getColumnIndex(CarContract.CarEntry.COLUMN_NAME)));
                intent.putExtra("image", mCursor.getInt(mCursor.getColumnIndex(CarContract.CarEntry.COLUMN_IMAGE)));
                intent.putExtra("cid", mCursor.getLong(mCursor.getColumnIndex(CarContract.CarEntry._ID)));

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
