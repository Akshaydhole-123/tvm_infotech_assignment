package com.CategoryService.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Category Table")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("categoryId")
    private int id;

    @JsonProperty("categoryName")
    private String categoryName;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    transient private List<Product> productList;


    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
        super();
    }

}

