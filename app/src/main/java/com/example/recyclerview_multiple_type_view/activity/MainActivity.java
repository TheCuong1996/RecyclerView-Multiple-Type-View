package com.example.recyclerview_multiple_type_view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.recyclerview_multiple_type_view.R;
import com.example.recyclerview_multiple_type_view.adapter.MenuFoodAdapter;
import com.example.recyclerview_multiple_type_view.model.BannerModel;
import com.example.recyclerview_multiple_type_view.model.InfoFoodModel;
import com.example.recyclerview_multiple_type_view.model.WrapperModel;
import com.example.recyclerview_multiple_type_view.other.Custom_Spansize_RCV;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MenuFoodAdapter mAdapter;
    private LinearLayout linearLayout;
    private Button btnRecommend, btnNear, btnGoodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setBtnRecommend();

        //Start Event
        btnRecommend.setOnClickListener(this);
        btnNear.setOnClickListener(this);
        btnGoodPrice.setOnClickListener(this);
        //End Event
    }

    private void init() {
        findViewById();

        initRCV();

        setAdapterRCV();
    }

    private void findViewById() {
        recyclerView = (RecyclerView) findViewById(R.id.rcvMenu);
        linearLayout = (LinearLayout) findViewById(R.id.line1);
        btnRecommend = (Button) findViewById(R.id.btnRecommend);
        btnNear = (Button) findViewById(R.id.btnNear);
        btnGoodPrice = (Button) findViewById(R.id.btnPrice);
    }

    private void initRCV() {

        GridLayoutManager gridLayoutManagerVertical = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        gridLayoutManagerVertical.setSpanSizeLookup(new Custom_Spansize_RCV(1, 2));

        recyclerView.setLayoutManager(gridLayoutManagerVertical);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setAdapterRCV() {
        WrapperModel wrapperModel = new WrapperModel(getListBanner(), getListArticle(), getListForYou(), getListRecommend(), getListNear(), getListGoodPrice());

        mAdapter = new MenuFoodAdapter(this, true, true, true, true, wrapperModel);
        recyclerView.setAdapter(mAdapter);

        initScrollListener();
    }

    private void initScrollListener() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (findFirstVisibleItemPosition > 2) {
                    linearLayout.setVisibility(View.VISIBLE);

                    switch (mAdapter.getCheck()) {
                        case 1:
                            setBtnRecommend();
                            break;
                        case 2:
                            setBtnNear();
                            break;
                        case 3:
                            setBtnGoodPrice();
                            break;
                        default:
                            break;
                    }
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecommend:
                mAdapter.checkOnclick(1);
                mAdapter.notifyDataSetChanged();
                setBtnRecommend();
                break;

            case R.id.btnNear:

                mAdapter.checkOnclick(2);
                mAdapter.notifyDataSetChanged();
                setBtnNear();
                break;

            case R.id.btnPrice:
                mAdapter.checkOnclick(3);
                mAdapter.notifyDataSetChanged();
                setBtnGoodPrice();

                break;

            default:
                break;
        }

    }

    private void setBtnRecommend() {
        btnRecommend.setTextColor(getResources().getColor(R.color.colorPrimary));
        btnNear.setTextColor(getResources().getColor(R.color.black));
        btnGoodPrice.setTextColor(getResources().getColor(R.color.black));

        btnRecommend.setPaintFlags(btnRecommend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnNear.setPaintFlags(btnNear.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        btnGoodPrice.setPaintFlags(btnGoodPrice.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    private void setBtnNear() {
        btnRecommend.setTextColor(getResources().getColor(R.color.black));
        btnNear.setTextColor(getResources().getColor(R.color.colorPrimary));
        btnGoodPrice.setTextColor(getResources().getColor(R.color.black));

        btnNear.setPaintFlags(btnRecommend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnRecommend.setPaintFlags(btnNear.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        btnGoodPrice.setPaintFlags(btnGoodPrice.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    private void setBtnGoodPrice() {
        btnRecommend.setTextColor(getResources().getColor(R.color.black));
        btnNear.setTextColor(getResources().getColor(R.color.black));
        btnGoodPrice.setTextColor(getResources().getColor(R.color.colorPrimary));

        btnGoodPrice.setPaintFlags(btnRecommend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnRecommend.setPaintFlags(btnNear.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
        btnNear.setPaintFlags(btnGoodPrice.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
    }

    private ArrayList<BannerModel> getListBanner() {
        ArrayList<BannerModel> _arBanner = new ArrayList<>();
        _arBanner.add(new BannerModel(1, R.mipmap.banner_1));
        _arBanner.add(new BannerModel(1, R.mipmap.banner_2));
        _arBanner.add(new BannerModel(1, R.mipmap.banner_3));
        return _arBanner;
    }

    private ArrayList<BannerModel> getListArticle() {
        ArrayList<BannerModel> _arArticle = new ArrayList<>();
        _arArticle.add(new BannerModel(1, R.mipmap.banner_4));
        _arArticle.add(new BannerModel(1, R.mipmap.banner_5));
        _arArticle.add(new BannerModel(1, R.mipmap.banner_6));
        return _arArticle;
    }

    private ArrayList<InfoFoodModel> getListForYou() {
        ArrayList<InfoFoodModel> _arInfoFood = new ArrayList<>();
        _arInfoFood.add(new InfoFoodModel(1, "Kai Coffee - Đường C18", R.mipmap.image_food_1, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "BlackBall - Chè & Trà Sữa Đài Loan - Khu Du Lịch Bến Xưa", R.mipmap.image_food_2, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "Oppa Milk Tea", R.mipmap.image_food_3, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "BiBi Fruits - Trái Cây Sân Bay", R.mipmap.image_food_4, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "Cửa Hàng Trái Cây Farm Của Tùng", R.mipmap.image_food_5, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Chợ Phố Fresh Food - Trái Cây Nhập Khẩu", R.mipmap.image_food_6, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "La Maison - Thịt Ngon Quốc Tế - Hoàng Hoa Thám", R.mipmap.image_food_7, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Shop Rượu Vang Trái Ngọt", R.mipmap.image_food_8, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Cá Hồi Annaseafresh - Phan Đình Phùng", R.mipmap.image_food_9, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Đặc Sản Nắng Gió - Thịt, Hải Sản & Khô Các Loại", R.mipmap.image_food_10, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(6, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.mipmap.image_food_36, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Royal Dimsum - Shop Online", R.mipmap.image_food_37, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.mipmap.image_food_38, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Trà Sữa Cha Cha", R.mipmap.image_food_39, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.mipmap.image_food_40, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(6, "Organicfood.vn - Siêu Thị Thực Phẩm Hữu Cơ", R.mipmap.image_food_16, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Happy Trade - Cộng Đồng Thực Phẩm Hữu Cơ", R.mipmap.image_food_17, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Meiwei - Dimsum & Hải Sản Hấp - Láng Hạ", R.mipmap.image_food_18, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Botanica Restaurant - Thái Phiên", R.mipmap.image_food_19, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Cha Go Tea & Caf'e - Nguyễn Khánh Toàn", R.mipmap.image_food_20, 0, 10, "100%"));
        return _arInfoFood;
    }

    private ArrayList<InfoFoodModel> getListRecommend() {
        ArrayList<InfoFoodModel> _arInfoFood = new ArrayList<>();
        _arInfoFood.add(new InfoFoodModel(1, "Cá Hồi Annaseafresh - Nguyễn Thái Sơn", R.mipmap.image_food_11, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "Vua Biển Đàm Vĩnh Hưng", R.mipmap.image_food_12, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "La Maison - Thịt Ngon Quốc Tế ", R.mipmap.image_food_13, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "La Maison - Thịt Ngon Quốc Tế - Lê Đức Thọ", R.mipmap.image_food_14, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "La Maison - Thịt Ngon Quốc Tế - Bùi Minh Trực", R.mipmap.image_food_15, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Organicfood.vn - Siêu Thị Thực Phẩm Hữu Cơ", R.mipmap.image_food_16, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Happy Trade - Cộng Đồng Thực Phẩm Hữu Cơ", R.mipmap.image_food_17, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Meiwei - Dimsum & Hải Sản Hấp - Láng Hạ", R.mipmap.image_food_18, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Botanica Restaurant - Thái Phiên", R.mipmap.image_food_19, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Cha Go Tea & Caf'e - Nguyễn Khánh Toàn", R.mipmap.image_food_20, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(1, "Kai Coffee - Đường C18", R.mipmap.image_food_1, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "BlackBall - Chè & Trà Sữa Đài Loan - Khu Du Lịch Bến Xưa", R.mipmap.image_food_2, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "Oppa Milk Tea", R.mipmap.image_food_3, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "BiBi Fruits - Trái Cây Sân Bay", R.mipmap.image_food_4, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(7, "Royal Dimsum - Shop Online", R.mipmap.image_food_37, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.mipmap.image_food_38, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Trà Sữa Cha Cha", R.mipmap.image_food_39, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.mipmap.image_food_40, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(5, "Cửa Hàng Trái Cây Farm Của Tùng", R.mipmap.image_food_5, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(5, "Dzui - Cơm Gà Hàn Quốc - Đặng Văn Ngữ", R.mipmap.image_food_25, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Juicybean - Nước Ép Trái Cây - Vincom Metropolis", R.mipmap.image_food_26, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Sinsa Korea Kitchen - Món Ăn Hàn Quốc", R.mipmap.image_food_27, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Sinsa Korea Kitchen - Món Ăn Hàn Quốc", R.mipmap.image_food_28, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Cơm Tấm Phương Anh - Tân Mai", R.mipmap.image_food_29, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Chè Thái Sầu Riêng - Nguyễn Quý Đức - Shop Online", R.mipmap.image_food_30, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(10, "Cha Go Tea & Caf'e - Nguyễn Khánh Toàn", R.mipmap.image_food_20, 0, 10, "100%"));
        return _arInfoFood;
    }

    private ArrayList<InfoFoodModel> getListNear() {
        ArrayList<InfoFoodModel> _arInfoFood = new ArrayList<>();
        _arInfoFood.add(new InfoFoodModel(1, "Trà Sữa Cha Tang", R.mipmap.image_food_21, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "Bánh Mì Bắp - Bánh Mì & Cơm", R.mipmap.image_food_22, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "Trà Sữa Hefkcha - Khâm Thiên ", R.mipmap.image_food_23, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "Royaltea - Trần Phú", R.mipmap.image_food_24, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "Dzui - Cơm Gà Hàn Quốc - Đặng Văn Ngữ", R.mipmap.image_food_25, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Juicybean - Nước Ép Trái Cây - Vincom Metropolis", R.mipmap.image_food_26, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Sinsa Korea Kitchen - Món Ăn Hàn Quốc", R.mipmap.image_food_27, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Sinsa Korea Kitchen - Món Ăn Hàn Quốc", R.mipmap.image_food_28, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Cơm Tấm Phương Anh - Tân Mai", R.mipmap.image_food_29, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Chè Thái Sầu Riêng - Nguyễn Quý Đức - Shop Online", R.mipmap.image_food_30, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(1, "Cá Hồi Annaseafresh - Nguyễn Thái Sơn", R.mipmap.image_food_11, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "Vua Biển Đàm Vĩnh Hưng", R.mipmap.image_food_12, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "La Maison - Thịt Ngon Quốc Tế ", R.mipmap.image_food_13, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "La Maison - Thịt Ngon Quốc Tế - Lê Đức Thọ", R.mipmap.image_food_14, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "La Maison - Thịt Ngon Quốc Tế - Bùi Minh Trực", R.mipmap.image_food_15, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Organicfood.vn - Siêu Thị Thực Phẩm Hữu Cơ", R.mipmap.image_food_16, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(5, "Pizza Xtrum - Hoàng Mai", R.mipmap.image_food_35, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.mipmap.image_food_36, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Royal Dimsum - Shop Online", R.mipmap.image_food_37, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.mipmap.image_food_38, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Trà Sữa Cha Cha", R.mipmap.image_food_39, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.mipmap.image_food_40, 0, 10, "100%"));
        return _arInfoFood;
    }

    private ArrayList<InfoFoodModel> getListGoodPrice() {
        ArrayList<InfoFoodModel> _arInfoFood = new ArrayList<>();
        _arInfoFood.add(new InfoFoodModel(1, "Hà Béo - Ăn Vặt Các Loại", R.mipmap.image_food_31, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "Mỏ Khoét Quán - Cơm Gà Sốt Cay Hàn Quốc - Vĩnh Hưng", R.mipmap.image_food_32, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "Bếp Tuệ An - Chè Khúc Bạch & Sữa Chua Phomai", R.mipmap.image_food_33, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "DV Fruit - Sinh Tố & Nước Ép Trái Cây", R.mipmap.image_food_34, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "Pizza Xtrum - Hoàng Mai", R.mipmap.image_food_35, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.mipmap.image_food_36, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "Royal Dimsum - Shop Online", R.mipmap.image_food_37, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.mipmap.image_food_38, 0, 10, "50%"));
        _arInfoFood.add(new InfoFoodModel(9, "Trà Sữa Cha Cha", R.mipmap.image_food_39, 0, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(10, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.mipmap.image_food_40, 0, 10, "100%"));
        _arInfoFood.add(new InfoFoodModel(1, "Kai Coffee - Đường C18", R.mipmap.image_food_1, 10, 20, "60%"));
        _arInfoFood.add(new InfoFoodModel(2, "BlackBall - Chè & Trà Sữa Đài Loan - Khu Du Lịch Bến Xưa", R.mipmap.image_food_2, 15, 20, "30%"));
        _arInfoFood.add(new InfoFoodModel(3, "Oppa Milk Tea", R.mipmap.image_food_3, 0, 10, "20%"));
        _arInfoFood.add(new InfoFoodModel(4, "BiBi Fruits - Trái Cây Sân Bay", R.mipmap.image_food_4, 20, 40, "80%"));
        _arInfoFood.add(new InfoFoodModel(5, "Cửa Hàng Trái Cây Farm Của Tùng", R.mipmap.image_food_5, 10, 20, "50%"));
        _arInfoFood.add(new InfoFoodModel(6, "Chợ Phố Fresh Food - Trái Cây Nhập Khẩu", R.mipmap.image_food_6, 10, 20, "40%"));
        _arInfoFood.add(new InfoFoodModel(7, "La Maison - Thịt Ngon Quốc Tế - Hoàng Hoa Thám", R.mipmap.image_food_7, 5, 10, "10%"));
        _arInfoFood.add(new InfoFoodModel(8, "Shop Rượu Vang Trái Ngọt", R.mipmap.image_food_8, 0, 10, "50%"));
        return _arInfoFood;
    }

}
