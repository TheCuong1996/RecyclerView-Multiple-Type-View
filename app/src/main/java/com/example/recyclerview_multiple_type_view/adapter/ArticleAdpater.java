package com.example.recyclerview_multiple_type_view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclerview_multiple_type_view.R;
import com.example.recyclerview_multiple_type_view.model.BannerModel;

import java.util.ArrayList;

public class ArticleAdpater extends RecyclerView.Adapter<ArticleAdpater.ViewHolder> {

    private Context mContext;
    private ArrayList<BannerModel> _arGrid;

    ArticleAdpater(Context context, ArrayList<BannerModel> arGrid) {
        this._arGrid = arGrid;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ArticleAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        final ArticleAdpater.ViewHolder viewHold;

        switch (i) {
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_gridview, viewGroup, false);
                viewHold = new ArticleAdpater.ViewHolder(view, i);
                return viewHold;

            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image_gridview, viewGroup, false);
                viewHold = new ArticleAdpater.ViewHolder(view, i);
                return viewHold;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdpater.ViewHolder viewHolder, int position) {

        Glide.with(mContext)
                .load(_arGrid.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder._imgView);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView _imgView;

        ViewHolder(@NonNull View itemView, int i) {
            super(itemView);
            _imgView = (ImageView) itemView.findViewById(R.id.imgGrid);
        }
    }
}
