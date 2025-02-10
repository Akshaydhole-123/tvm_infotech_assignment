package com.ProductService.Interface;

import com.ProductService.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {
    ResponseEntity<String> createProduct(Product product);

 List<Product> getProductsByCategory(Integer categoryId);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<String> updateByProductId(int productId, Product product);

    ResponseEntity<?> getByProductId(int productId);

    ResponseEntity<String> deleteByProductId(int productId);

    ResponseEntity<?> getCategoryByProductPrice(Integer categoryId, Double productPrice);
}
