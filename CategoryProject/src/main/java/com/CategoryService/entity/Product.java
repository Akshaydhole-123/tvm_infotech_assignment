package com.CategoryService.entity;


public class Product {

    private int productId;

    private String productName;

    private double productPrice;

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public int getproductId() {
        return productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setproductId(int id) {
        this.productId = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Product(int productId, String productName, double productPrice, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
    }

    public Product() {
        super();
    }


}
