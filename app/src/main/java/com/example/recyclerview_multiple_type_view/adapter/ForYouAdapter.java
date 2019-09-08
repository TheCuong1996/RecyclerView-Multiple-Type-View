package com.example.recyclerview_multiple_type_view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclerview_multiple_type_view.R;
import com.example.recyclerview_multiple_type_view.model.InfoFoodModel;

import java.util.ArrayList;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<InfoFoodModel> _arInfoFood;

    public ForYouAdapter(ArrayList<InfoFoodModel> arInfoFood, Context context) {
        this._arInfoFood = arInfoFood;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ForYouAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_info_food, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ForYouAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext)
                .load(_arInfoFood.get(i).getLogo_link())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);
        viewHolder.tvNameShop.setText(_arInfoFood.get(i).getName());
        viewHolder.tvTimmeMin.setText(_arInfoFood.get(i).getShipping_duration_min() + " -");
        viewHolder.tvTimeMax.setText(_arInfoFood.get(i).getShipping_duration_max() + " phút");
        viewHolder.tvDiscount.setText("Giảm "+_arInfoFood.get(i).getPromotion_text());
    }

    @Override
    public int getItemCount() {
        return _arInfoFood.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvNameShop, tvTimmeMin,tvTimeMax, tvDiscount;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imgFood);
            tvNameShop = (TextView) itemView.findViewById(R.id.tvNameShop);
            tvTimmeMin = (TextView) itemView.findViewById(R.id.tvTimeMin);
            tvTimeMax = (TextView) itemView.findViewById(R.id.tvTimeMax);
            tvDiscount = (TextView) itemView.findViewById(R.id.tvDisCount);
        }
    }
}
