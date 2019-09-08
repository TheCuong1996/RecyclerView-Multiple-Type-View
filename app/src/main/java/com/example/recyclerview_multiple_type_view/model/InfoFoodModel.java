package com.example.recyclerview_multiple_type_view.model;

public class InfoFoodModel {
    private int id;
    private String name;
    private int logo_link;
    private int shipping_duration_min;
    private int shipping_duration_max;
    private String promotion_text;

    public InfoFoodModel(int id, String name, int logo_link, int shipping_duration_min, int shipping_duration_max, String promotion_text) {
        this.id = id;
        this.name = name;
        this.logo_link = logo_link;
        this.shipping_duration_min = shipping_duration_min;
        this.shipping_duration_max = shipping_duration_max;
        this.promotion_text = promotion_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogo_link() {
        return logo_link;
    }

    public void setLogo_link(int logo_link) {
        this.logo_link = logo_link;
    }

    public int getShipping_duration_min() {
        return shipping_duration_min;
    }

    public void setShipping_duration_min(int shipping_duration_min) {
        this.shipping_duration_min = shipping_duration_min;
    }

    public int getShipping_duration_max() {
        return shipping_duration_max;
    }

    public void setShipping_duration_max(int shipping_duration_max) {
        this.shipping_duration_max = shipping_duration_max;
    }

    public String getPromotion_text() {
        return promotion_text;
    }

    public void setPromotion_text(String promotion_text) {
        this.promotion_text = promotion_text;
    }
}
