package com.example.onlineshop;

public class WishListModel {

    private int productImage;
    private String productTitle;
    private int freeCoupens;
    private String rating;
    private int totalratings;
    private String productPrice;
    private String cuttedPrice;
    private String paymentMethod;

    public WishListModel(int productImage, String productTitle, int freeCoupens, String rating, int totalratings, String productPrice, String cuttedPrice, String paymentMethod) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupens = freeCoupens;
        this.rating = rating;
        this.totalratings = totalratings;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.paymentMethod = paymentMethod;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getFreeCoupens() {
        return freeCoupens;
    }

    public void setFreeCoupens(int freeCoupens) {
        this.freeCoupens = freeCoupens;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotalratings() {
        return totalratings;
    }

    public void setTotalratings(int totalratings) {
        this.totalratings = totalratings;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
