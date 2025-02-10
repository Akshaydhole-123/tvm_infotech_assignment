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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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













}
