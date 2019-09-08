package com.example.recyclerview_multiple_type_view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclerview_multiple_type_view.model.BannerModel;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<BannerModel> _arBanner;

    public BannerAdapter(Context context, ArrayList<BannerModel> arBanner) {
        this.mContext = context;
        this._arBanner = arBanner;
    }

    @Override
    public int getCount() {
        return _arBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext)
                .load(_arBanner.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
