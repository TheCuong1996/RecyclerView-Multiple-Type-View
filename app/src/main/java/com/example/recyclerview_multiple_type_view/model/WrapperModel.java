package com.example.recyclerview_multiple_type_view.model;

import java.util.ArrayList;

public class WrapperModel {
    private ArrayList<BannerModel> _arBanner;
    private ArrayList<BannerModel> _arArticle;
    private ArrayList<InfoFoodModel> _arForYou;
    private ArrayList<InfoFoodModel> _arRecommend;
    private ArrayList<InfoFoodModel> _arListNear;
    private ArrayList<InfoFoodModel> _arListGoodPrice;


    public WrapperModel(ArrayList<BannerModel> _arBanner, ArrayList<BannerModel> _arArticle, ArrayList<InfoFoodModel> _arForYou, ArrayList<InfoFoodModel> _arRecommend, ArrayList<InfoFoodModel> _arListNear, ArrayList<InfoFoodModel> _arListGoodPrice) {
        this._arBanner = _arBanner;
        this._arArticle = _arArticle;
        this._arForYou = _arForYou;
        this._arRecommend = _arRecommend;
        this._arListNear = _arListNear;
        this._arListGoodPrice = _arListGoodPrice;
    }

    public ArrayList<BannerModel> get_arBanner() {
        return _arBanner;
    }

    public void set_arBanner(ArrayList<BannerModel> _arBanner) {
        this._arBanner = _arBanner;
    }

    public ArrayList<BannerModel> get_arArticle() {
        return _arArticle;
    }

    public void set_arArticle(ArrayList<BannerModel> _arArticle) {
        this._arArticle = _arArticle;
    }

    public ArrayList<InfoFoodModel> get_arForYou() {
        return _arForYou;
    }

    public void set_arForYou(ArrayList<InfoFoodModel> _arForYou) {
        this._arForYou = _arForYou;
    }

    public ArrayList<InfoFoodModel> get_arRecommend() {
        return _arRecommend;
    }

    public void set_arRecommend(ArrayList<InfoFoodModel> _arRecommend) {
        this._arRecommend = _arRecommend;
    }

    public ArrayList<InfoFoodModel> get_arListNear() {
        return _arListNear;
    }

    public void set_arListNear(ArrayList<InfoFoodModel> _arListNear) {
        this._arListNear = _arListNear;
    }

    public ArrayList<InfoFoodModel> get_arListGoodPrice() {
        return _arListGoodPrice;
    }

    public void set_arListGoodPrice(ArrayList<InfoFoodModel> _arListGoodPrice) {
        this._arListGoodPrice = _arListGoodPrice;
    }
}
