package com.ProductService;

import com.ProductService.Interface.ProductInterface;
import com.ProductService.controller.ProductController;
import com.ProductService.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {


    @InjectMocks
    private ProductController productController;

    @Mock
     private ProductInterface productInterface;


    @BeforeEach
     void setUp(){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    @Order(1)
    public void test_getAllProduct(){
        List<Product> product= Arrays.asList(new Product(1,"splender",50000.0,101),new Product(2,"palser",60000.0,102));

        when(productInterface.getAllProducts()).thenReturn(new ResponseEntity(product,HttpStatus.OK));
         ResponseEntity<?> response= productController.getAllProducts();
         Assertions.assertNotNull(response);
         Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
         Assertions.assertEquals(product,response.getBody());

    }
    @Test
    public void test_createProduct(){

        Product product=new Product(1,"splender",45000.0,101);

        when(productInterface.createProduct(product)).thenReturn(new ResponseEntity<>("Product Created Successfully",HttpStatus.OK));
        ResponseEntity<String> response =productController.createProduct(product);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Product Created Successfully",response.getBody());
    }
    @Test
    public void test_getProdcutId(){

        Product product=new Product(1,"splender",45000.0,101);
       when(productInterface.getByProductId(1)).thenReturn(new ResponseEntity(product,HttpStatus.OK));

       ResponseEntity<?> response=productController.getByProductId(1);

       Assertions.assertNotNull(response);
       Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
       Assertions.assertEquals(product,response.getBody());

    }
    @Test
    public void test_getCategoryById(){
        List<Product> product=  Arrays.asList(new Product(1,"splender",45000.0,101));

        when(productInterface.getProductsByCategory(101)).thenReturn( product);
       List<Product> list= productController.getProductsByCategory(101);
      Assertions.assertNotNull(list);
       Assertions.assertEquals(1,list.size());
      Assertions.assertEquals("splender",list.get(0).getProductName());
    }
    @Test
    public void test_updateProduct(){
        Product product=new Product(1,"splender",45000.0,101);
        when(productInterface.updateByProductId(1,product)).thenReturn(new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK));
        ResponseEntity<String> response=productController.updateByProduct(1,product);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Product Updated Successfully",response.getBody());
    }
    @Test
    public void test_deletePeoduct(){
        Product product=new Product(1,"splender",45000.0,101);
        when(productInterface.deleteByProductId(1)).thenReturn(new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK));
        ResponseEntity<String> response=productController.deleteByProduct(1);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Product Deleted Successfully",response.getBody());
    }
    @Test
    public void test_GetCategoryIdAndProductPrice(){
        List<Product> product= Arrays.asList(new Product(1,"splender",45000.0,101));
        int id=101;
        double price=45000.0;
        when(productInterface.getCategoryByProductPrice(id,price)).thenReturn(new ResponseEntity(product,HttpStatus.OK));
        ResponseEntity<?> response=productController.getCategoryIdByProductPrice(id,price);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals(product,response.getBody());
    }



}
