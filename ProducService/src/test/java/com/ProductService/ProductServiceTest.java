package com.ProductService;


import com.ProductService.Repository.ProductRepository;
import com.ProductService.entity.Product;
import com.ProductService.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;
    private List<Product> productList;

    @BeforeEach
    public void setUp() {

        product = new Product();
        product.setProductId(1);
        product.setProductName("Test");
        product.setProductPrice(4000.0);
        product.setCategoryId(101);

        productList = new ArrayList<>();
        productList.add(new Product(1, "Test", 4000.0, 102));

    }

    @Test
    public void test_GetAllProducts() {


        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        ResponseEntity<?> response = productService.getAllProducts();
        List<Product> products = (List<Product>) response.getBody();

        Assertions.assertNotNull(products);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Test", products.get(0).getProductName());


    }

    @Test
    public void test_GetAllProductsDoesNotExists() {

        when(productRepository.findAll()).thenReturn(null);
        ResponseEntity<?> response = productService.getAllProducts();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Product Does Not exists", response.getBody());

    }

    @Test
    public void test_CreateProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(product);
        ResponseEntity<String> response = productService.createProduct(product);
        Assertions.assertEquals("Product Created Successfully", response.getBody());

    }

    @Test
    public void test_CreateProductDoesNotCreate() {
        when(productRepository.save(any(Product.class))).thenReturn(null);
        ResponseEntity<String> response = productService.createProduct(null);
        Assertions.assertEquals("Error while creating product", response.getBody());

    }

    @Test
    public void test_ProductById() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ResponseEntity<?> response = productService.getByProductId(1);
        Product foundProduct = (Product) response.getBody();
        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(1, foundProduct.getProductId());
        Assertions.assertEquals("Test", foundProduct.getProductName());

    }

    @Test
    public void test_ProductByIdDoesNotGet() {
        int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        ResponseEntity<?> response = productService.getByProductId(id);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Product does not Exists", response.getBody());

    }

    @Test
    public void test_DeleteProduct() {

        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(product));
        ResponseEntity<String> response = productService.deleteByProductId(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Product Deleted Successfully", response.getBody());
    }

    @Test
    public void test_DeleteProductDoesNotExists() {

        when(productRepository.findById(null)).thenReturn(Optional.ofNullable(null));
        ResponseEntity<String> response = productService.deleteByProductId(1);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Error while Deleted product", response.getBody());
    }

    @Test
    public void test_UpdateProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(product);
        ResponseEntity<String> response = productService.updateByProductId(1, product);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Product Updated Successfully", response.getBody());
    }

    @Test
    public void test_UpdateProductIdDoesNotExists() {

        when(productRepository.save(null)).thenReturn(Optional.ofNullable(null));
        ResponseEntity<String> response = productService.updateByProductId(0, null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Error while updating Product", response.getBody());
    }

    @Test
    public void test_CategoryById() {

        when(productRepository.findByCategoryId(101)).thenReturn(List.of(product));
        List<Product> productList = productService.getProductsByCategory(101);
        Assertions.assertNotNull(productList);
        Assertions.assertEquals(1, productList.size());
        Assertions.assertEquals("Test", productList.get(0).getProductName());

    }

    @Test
    public void test_CategoryByIdDoesNotExist() {

        when(productRepository.findByCategoryId(1)).thenReturn(new ArrayList<>());
        List<Product> productList = productService.getProductsByCategory(1);
        Assertions.assertNull(productList);

    }

    @Test
    public void test_getProductsByCategoryIdAndPrice() {
        int id = 102;
        double price = 4000.0;
        when(productRepository.findByCategoryIdAndProductPrice(id,
                price)).thenReturn(productList);
        ResponseEntity<?> response = productService.getCategoryByProductPrice(id, price);
        List<Product> products = (List<Product>) response.getBody();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(id, products.get(0).getCategoryId());
    }

    @Test
    public void test_getProductsByCategoryIdAndPrice_onlyGetProductPrice() {

        double price = 100.0;
        when(productRepository.findByProductPrice(price)).thenReturn(productList);
        ResponseEntity<?> response = productService.getCategoryByProductPrice(null, price);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(productList, response.getBody());
    }

    @Test
    public void test_getProductsByCategoryIdAndPrice_onlyGetProductCategoryId() {

        int id = 1;
        when(productRepository.findByCategoryId(id)).thenReturn(productList);
        ResponseEntity<?> response = productService.getCategoryByProductPrice(id, null);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(productList, response.getBody());
    }

    @Test
    public void test_getProductsByCategoryIdAndPrice_ButPriceAndCategoryIdDoesMatch() {

        int id = 1;
        double price = 100.0;
        when(productRepository.findByCategoryIdAndProductPrice(id, price)).thenReturn(new ArrayList<>());
        ResponseEntity<?> response = productService.getCategoryByProductPrice(id, price);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Price And Category does Not Exists", response.getBody());
    }
}
