package com.example.onlineshop;

public class MyOrderItemModel {

    private int productImage;
    private int rating;
    private String productTitle;
    private String deleveryStatus;

    public MyOrderItemModel(int productImage, int rating, String productTitle, String deleveryStatus) {
        this.productImage = productImage;
        this.rating = rating;
        this.productTitle = productTitle;
        this.deleveryStatus = deleveryStatus;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDeleveryStatus() {
        return deleveryStatus;
    }

    public void setDeleveryStatus(String deleveryStatus) {
        this.deleveryStatus = deleveryStatus;
    }
}
