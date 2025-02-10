package com.ProductService;


import com.ProductService.Repository.ProductRepository;
import com.ProductService.entity.Product;
import com.ProductService.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_GetAllProducts(){

        List<Product> productList= Arrays.asList(new Product(9,"pop",90,3));

        Mockito.when(productRepository.findAll()).thenReturn(productList);

        ResponseEntity<List<Product>> response= (ResponseEntity<List<Product>>) productService.getAllProducts();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(1,response.getBody().size());


    }
    @Test
    public void test_CreateProduct(){

        Product product=new Product(10,"product",100,201);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        ResponseEntity<String> response=productService.createProduct(product);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());

    }
    @Test
    public  void test_ProductById(){

        Product product=new Product(10,"product",100,201);
        Mockito.when(productRepository.findById(10)).thenReturn(Optional.of(product));
   
       ResponseEntity<Product> response= (ResponseEntity<Product>) productService.getByProductId(10);
       
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertEquals(product.getProductId(),response.getBody().getProductId());
   
   
    }
    @Test
    public void test_DeleteProduct(){

        Product product=new Product(10,"product",100,201);
        Integer productId=10;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<String> response=productService.deleteByProductId(productId);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals("Product Deleted Successfully",response.getBody());


    }
    @Test
    public void test_UpdateProduct(){
        Product product=new Product(10,"product",100,201);
        Integer productId=10;

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        ResponseEntity<String> response=productService.updateByProductId(productId,product);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        assertEquals("Product Updated Successfully",response.getBody());

    }

    @Test
    public void test_CategoryById(){
        Product product=new Product(10,"product",100,201);
        Integer categoryId=101;
        Mockito.when(productRepository.findByCategoryId(categoryId)).thenReturn((List<Product>) product);

       List<Product> productList=  productService.getProductsByCategory(categoryId);
        assertEquals(1,productList.size());
        assertEquals("product",productList.get(1).getProductName());

    }

    @Test
    public void test_getProductsByCategoryIdAndPrice() {
        Double productPrice = 101.0;
        Integer categoryId = 105;
        List<Product> productList = Collections.singletonList(new Product(9, "pop", productPrice, categoryId));

        Mockito.when(productRepository.findByCategoryIdAndProductPrice(categoryId,productPrice)).thenReturn(productList);

ResponseEntity<List<Product>> response= (ResponseEntity<List<Product>>) productService.getCategoryByProductPrice(categoryId,productPrice);

assertEquals(HttpStatus.OK,response.getStatusCode());
assertEquals(1,response.getBody().size());
    }
}
