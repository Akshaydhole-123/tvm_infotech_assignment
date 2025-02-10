package com.ProductService.service;


import com.ProductService.Interface.ProductInterface;
import com.ProductService.entity.Product;
import com.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<String> createProduct(Product product) {
        try{
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating category");
        }
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryId) {
        List<Product> products= productRepository.findByCategoryId(categoryId);
        if (!products.isEmpty())
            return products;
        return null;

    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        try {
            if (!products.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(products);

        } catch (Exception e) {
            e.printStackTrace();        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Does Not exists");
    }

    @Override
    public ResponseEntity<String> updateByProductId(int productId, Product product) {
        try{
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating category");
        }
    }

    @Override
    public ResponseEntity<?> getByProductId(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        try {
            if (product.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(product.get());

        }catch (Exception e) {
            e.printStackTrace();
        }
               return ResponseEntity.status(HttpStatus.OK).body("Product does not Exists");
    }

    @Override
    public ResponseEntity<String> deleteByProductId(int productId) {
        try{
            Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent()) {
                productRepository.delete(product.get());
            }
            return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while Deleted product");
        }
    }

    @Override
    public ResponseEntity<?> getCategoryByProductPrice(Integer categoryId, Double productPrice) {

        ArrayList<Product> productList;
      try {
          if (categoryId != null && productPrice != null) {
              productList = (ArrayList<Product>) productRepository.findByCategoryIdAndProductPrice(categoryId, productPrice);
          } else if (categoryId != null) {
              productList = (ArrayList<Product>) productRepository.findByCategoryId(categoryId);
          } else if (productPrice != null) {
              productList = (ArrayList<Product>) productRepository.findByProductPrice(productPrice);
          } else {
              productList = (ArrayList<Product>) productRepository.findAll();
          }
          if (!productList.isEmpty()) {
              return ResponseEntity.status(HttpStatus.OK).body(productList);
          } else {
              return ResponseEntity.status(HttpStatus.OK).body("Price And Category does Not Exists");
          }
      }catch (Exception e){

          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.OK).body("Invalid Data");
      }
    }
    }




