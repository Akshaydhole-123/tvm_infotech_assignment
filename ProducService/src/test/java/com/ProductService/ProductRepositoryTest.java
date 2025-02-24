package com.ProductService;


import com.ProductService.Repository.ProductRepository;
import com.ProductService.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = ProductServiceApplication.class)
@Transactional
@Rollback

public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test_FindCategoryId(){

        Product products = new Product(0, "bike", 500.0, 101);
        productRepository.saveAll(List.of(products));
        List<Product> productList= productRepository.findByCategoryId(101);
        Assertions.assertFalse(productList.isEmpty());
        Assertions.assertEquals(1,productList.size());
    }
    @Test
    public void test_FindCategoryAndProductPrice(){

        Product products = new Product(0, "bike", 500.0, 101);
        productRepository.saveAll(List.of(products));
        List<Product> productList= productRepository.findByCategoryIdAndProductPrice(101,500.0);
        Assertions.assertEquals(1,productList.size());
        Assertions.assertEquals(101,productList.get(0).getCategoryId());
        Assertions.assertEquals(500.0,productList.get(0).getProductPrice());

    }

    @Test
    public void test_findByProductPrice(){
        Product products = new Product(0, "bike", 500.0, 101);
        productRepository.saveAll(List.of(products));
        List<Product> productList= productRepository.findByProductPrice(500.0);
        Assertions.assertFalse(productList.isEmpty());
        Assertions.assertEquals(500.0,productList.get(0).getProductPrice());

    }





}
