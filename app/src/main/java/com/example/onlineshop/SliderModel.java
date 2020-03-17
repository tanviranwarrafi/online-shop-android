package com.example.onlineshop;

public class SliderModel {
    private String banner;
    private String bacgroundColor;

    public SliderModel(String banner, String bacgroundColor) {
        this.banner = banner;
        this.bacgroundColor = bacgroundColor;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBacgroundColor() {
        return bacgroundColor;
    }

    public void setBacgroundColor(String bacgroundColor) {
        this.bacgroundColor = bacgroundColor;
    }
}
