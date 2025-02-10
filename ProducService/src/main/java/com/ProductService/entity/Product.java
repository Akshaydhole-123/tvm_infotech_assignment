package com.ProductService.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProductTable")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id

    @JsonProperty("productId")
    private int productId;

    @JsonProperty("productName")
    private  String productName;

    @JsonProperty("productPrice")
    private double productPrice;

    public Product(int productId,String productName,double productPrice,int categoryId) {
        this.productId=productId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.categoryId=categoryId;
    }

    @JsonProperty("categoryId")
    private int categoryId;



}
