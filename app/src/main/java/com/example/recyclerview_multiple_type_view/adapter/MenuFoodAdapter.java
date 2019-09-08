package com.example.recyclerview_multiple_type_view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclerview_multiple_type_view.R;
import com.example.recyclerview_multiple_type_view.model.InfoFoodModel;
import com.example.recyclerview_multiple_type_view.model.WrapperModel;
import com.example.recyclerview_multiple_type_view.other.Custom_Spansize;
import com.example.recyclerview_multiple_type_view.other.GridSpacingItemDecoration;
import com.example.recyclerview_multiple_type_view.other.ItemClickListener;

import java.util.ArrayList;

public class MenuFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean isVIEWPAGER, isGRIDVIEW, isRCVHORIZONTAL, isTAB;
    private static final int VIEW_VIEWPAGER = 0;
    private static final int VIEW_GRIDVIEW = 1;
    private static final int VIEW_RCVHORIZONTAL = 2;
    private static final int VIEW_TAB = 3;
    private static final int VIEW_ITEM = 4;
    private Context mContext;
    private WrapperModel wrapperModel;
    private ArrayList<InfoFoodModel> _arListMain;
    private int check = 0;
    private int click = 0;

    public MenuFoodAdapter(Context context, boolean isVIEWPAGER, boolean isGRIDVIEW, boolean isRCVHORIZONTAL, boolean isTAB, WrapperModel wrapperModel) {
        this.mContext = context;
        this.isVIEWPAGER = isVIEWPAGER;
        this.isGRIDVIEW = isGRIDVIEW;
        this.isRCVHORIZONTAL = isRCVHORIZONTAL;
        this.isTAB = isTAB;
        this.wrapperModel = wrapperModel;
        this._arListMain = wrapperModel.get_arRecommend();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_VIEWPAGER) {
            View viewHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_banner, parent, false);
            return new BannerViewHolder(viewHeader);
        } else if (viewType == VIEW_GRIDVIEW) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_layout, parent, false);
            return new ArticleViewHolder(viewItem);
        } else if (viewType == VIEW_RCVHORIZONTAL) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foryou_layout, parent, false);
            return new ForYouViewHolder(viewItem);
        } else if (viewType == VIEW_TAB) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab_layout, parent, false);
            return new TabViewHolder(viewItem);
        } else if (viewType == VIEW_ITEM) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_food, parent, false);
            return new InfoFoodHolder(viewItem);
        } else
            throw new RuntimeException("Could not inflate layout");
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerAdapter imageViewPagerAdapter = new BannerAdapter(mContext, wrapperModel.get_arBanner());
            ((BannerViewHolder) holder).viewPager.setAdapter(imageViewPagerAdapter);

        } else if (holder instanceof ArticleViewHolder) {
            GridLayoutManager gridLayoutManagerVertical = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
            gridLayoutManagerVertical.setSpanSizeLookup(new Custom_Spansize(2, 1));
            ((ArticleViewHolder) holder).rcvArticle.setLayoutManager(gridLayoutManagerVertical);
            ((ArticleViewHolder) holder).rcvArticle.addItemDecoration(new GridSpacingItemDecoration());
            ((ArticleViewHolder) holder).rcvArticle.setHasFixedSize(true);

            ArticleAdpater adapter = new ArticleAdpater(mContext, wrapperModel.get_arArticle());
            ((ArticleViewHolder) holder).rcvArticle.setAdapter(adapter);

        } else if (holder instanceof ForYouViewHolder) {
            ForYouAdapter forYouAdapter = new ForYouAdapter(wrapperModel.get_arForYou(), mContext);
            ((ForYouViewHolder) holder).rcvForYou.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            ((ForYouViewHolder) holder).rcvForYou.setItemAnimator(new DefaultItemAnimator());
            ((ForYouViewHolder) holder).rcvForYou.setHasFixedSize(true);
            ((ForYouViewHolder) holder).rcvForYou.setAdapter(forYouAdapter);

        } else if (holder instanceof TabViewHolder) {

            setBtnRecommend(holder);

            ((TabViewHolder) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {
                    switch (v.getId()) {
                        case R.id.btnRecommend:

                            _arListMain = wrapperModel.get_arRecommend();

                            setBtnRecommend(holder);

                            notifyItemRangeChanged(4, _arListMain.size() + 4);

                            setCheck(1);

                            break;

                        case R.id.btnNear:

                            _arListMain = wrapperModel.get_arListNear();

                            setBtnGan(holder);

                            notifyItemRangeChanged(4, _arListMain.size() + 4);

                            setCheck(2);

                            break;

                        case R.id.btnPrice:

                            _arListMain = wrapperModel.get_arListGoodPrice();

                            setBtnPrice(holder);

                            notifyItemRangeChanged(4, _arListMain.size() + 4);

                            setCheck(3);

                            break;

                        default:
                            break;
                    }
                }
            });

            if (click != 0) {
                switch (click) {
                    case 1:
                        setBtnRecommend(holder);
                        setCheck(1);
                        break;
                    case 2:
                        setBtnGan(holder);
                        setCheck(2);
                        break;
                    case 3:
                        setBtnPrice(holder);
                        setCheck(3);
                        break;
                    default:
                        break;
                }
            }
        } else if (holder instanceof InfoFoodHolder) {
            Glide.with(mContext)
                    .load(_arListMain.get(position - 4).getLogo_link())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((InfoFoodHolder) holder).imageView);
            ((InfoFoodHolder) holder).tvNameShop.setText(_arListMain.get(position - 4).getName());
            ((InfoFoodHolder) holder).tvTimmeMin.setText(_arListMain.get(position - 4).getShipping_duration_min() + " -");
            ((InfoFoodHolder) holder).tvTimeMax.setText(_arListMain.get(position - 4).getShipping_duration_max() + " phút");
            ((InfoFoodHolder) holder).tvDiscount.setText("Giảm " + _arListMain.get(position - 4).getPromotion_text());
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = _arListMain.size();
        if (isVIEWPAGER && isGRIDVIEW && isRCVHORIZONTAL && isTAB) {
            itemCount = itemCount + 4;
        }
        Log.d("TheCuong", "size " + itemCount);
        return itemCount;
    }

    private boolean isVIEWPAGER(int position) {
        return position == 0;
    }

    private boolean isGRIDVIEW(int position) {
        return position == 1;
    }

    private boolean isRCVHORIZONTAL(int position) {
        return position == 2;
    }

    private boolean isTab(int position) {
        return position == 3;
    }

    public int getCheck() {
        return check;
    }

    @Override
    public int getItemViewType(int position) {
        if (isVIEWPAGER && isVIEWPAGER(position)) {
            return VIEW_VIEWPAGER;
        } else if (isGRIDVIEW && isGRIDVIEW(position)) {
            return VIEW_GRIDVIEW;
        } else if (isRCVHORIZONTAL && isRCVHORIZONTAL(position)) {
            return VIEW_RCVHORIZONTAL;
        } else if (isTAB && isTab(position)) {
            return VIEW_TAB;
        } else return VIEW_ITEM;
    }

    private void setBtnRecommend(@NonNull RecyclerView.ViewHolder viewHolder) {
        ((TabViewHolder) viewHolder).btnRecommend.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        ((TabViewHolder) viewHolder).btnNear.setTextColor(mContext.getResources().getColor(R.color.black));
        ((TabViewHolder) viewHolder).btnPrice.setTextColor(mContext.getResources().getColor(R.color.black));

        ((TabViewHolder) viewHolder).btnRecommend.setPaintFlags(((TabViewHolder) viewHolder).btnRecommend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ((TabViewHolder) viewHolder).btnNear.setPaintFlags(((TabViewHolder) viewHolder).btnNear.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        ((TabViewHolder) viewHolder).btnPrice.setPaintFlags(((TabViewHolder) viewHolder).btnPrice.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    private void setBtnGan(@NonNull RecyclerView.ViewHolder viewHolder) {
        ((TabViewHolder) viewHolder).btnRecommend.setTextColor(mContext.getResources().getColor(R.color.black));
        ((TabViewHolder) viewHolder).btnNear.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        ((TabViewHolder) viewHolder).btnPrice.setTextColor(mContext.getResources().getColor(R.color.black));

        ((TabViewHolder) viewHolder).btnNear.setPaintFlags(((TabViewHolder) viewHolder).btnNear.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ((TabViewHolder) viewHolder).btnRecommend.setPaintFlags(((TabViewHolder) viewHolder).btnRecommend.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        ((TabViewHolder) viewHolder).btnPrice.setPaintFlags(((TabViewHolder) viewHolder).btnPrice.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    private void setBtnPrice(@NonNull RecyclerView.ViewHolder viewHolder) {
        ((TabViewHolder) viewHolder).btnRecommend.setTextColor(mContext.getResources().getColor(R.color.black));
        ((TabViewHolder) viewHolder).btnNear.setTextColor(mContext.getResources().getColor(R.color.black));
        ((TabViewHolder) viewHolder).btnPrice.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));

        ((TabViewHolder) viewHolder).btnPrice.setPaintFlags(((TabViewHolder) viewHolder).btnPrice.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        ((TabViewHolder) viewHolder).btnRecommend.setPaintFlags(((TabViewHolder) viewHolder).btnRecommend.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        ((TabViewHolder) viewHolder).btnNear.setPaintFlags(((TabViewHolder) viewHolder).btnNear.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    public void checkOnclick(int position) {

        switch (position) {
            case 1:
                _arListMain = wrapperModel.get_arRecommend();
                notifyItemRangeChanged(4, _arListMain.size() + 4);
                break;

            case 2:
                _arListMain = wrapperModel.get_arListNear();
                notifyItemRangeChanged(4, _arListMain.size() + 4);
                break;

            case 3:
                _arListMain = wrapperModel.get_arListGoodPrice();
                notifyItemRangeChanged(4, _arListMain.size() + 4);
                break;

            default:
                break;
        }
        click = position;
    }

    private void setCheck(int position) {
        this.check = position;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        private ViewPager viewPager;

        BannerViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewpager);
        }
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcvArticle;

        ArticleViewHolder(View itemView) {
            super(itemView);
            rcvArticle = (RecyclerView) itemView.findViewById(R.id.rcvArticle);
        }
    }

    public static class ForYouViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rcvForYou;

        ForYouViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvForYou = (RecyclerView) itemView.findViewById(R.id.rcvForYou);
        }
    }

    public class TabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button btnRecommend, btnNear, btnPrice;
        private ItemClickListener itemClickListener;

        TabViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRecommend = (Button) itemView.findViewById(R.id.btnRecommend);
            btnNear = (Button) itemView.findViewById(R.id.btnNear);
            btnPrice = (Button) itemView.findViewById(R.id.btnPrice);

            btnRecommend.setOnClickListener(this);
            btnNear.setOnClickListener(this);
            btnPrice.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }

    }

    public static class InfoFoodHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvNameShop, tvTimmeMin, tvTimeMax, tvDiscount;

        InfoFoodHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgFood);
            tvNameShop = (TextView) itemView.findViewById(R.id.tvNameShop);
            tvTimmeMin = (TextView) itemView.findViewById(R.id.tvTimeMin);
            tvTimeMax = (TextView) itemView.findViewById(R.id.tvTimeMax);
            tvDiscount = (TextView) itemView.findViewById(R.id.tvDisCount);
        }
    }
}
