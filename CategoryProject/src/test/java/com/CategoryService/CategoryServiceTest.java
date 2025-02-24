package com.CategoryService;

import com.CategoryService.Interface.ProductClient;
import com.CategoryService.entity.Category;
import com.CategoryService.entity.Product;
import com.CategoryService.repository.CategoryRepository;
import com.CategoryService.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    CategoryService categoryService;

    private Category category1, category3;
    private Product product1;

    @BeforeEach
    public void setUp(){

        category1= new Category(103,"Bike");
        product1 =new Product(1,"splender",40000,103);
        category3=new Category();
    }

    @Test
    @Order(1)
    public void test_getAllCategory(){

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1));
        when(productClient.getProductsByCategory(category1.getId())).thenReturn(Arrays.asList(product1));
        ResponseEntity<?> response=  categoryService.getAllCategory();
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertInstanceOf(List.class, response.getBody());
        assertEquals(1,((List<?>) response.getBody()).size());

    }
    @Test
    public void test_AllCategoryNotExist(){

        when(categoryRepository.findAll()).thenReturn(null);
        ResponseEntity<?> response=  categoryService.getAllCategory();
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals("Category Does Not Exists", response.getBody());

    }
    @Test
    @Order(2)
    public void test_getByCategory(){

        int id = 103;
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(category1));
        Mockito.when(productClient.getProductsByCategory(id)).thenReturn(Arrays.asList(product1));
        ResponseEntity<?> response= categoryService.getByCategory(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertTrue(response.getBody() instanceof Category);
        Category categoryResponse=(Category) response.getBody();
        assertNotNull(categoryResponse);
        assertEquals("Bike", ((Category) response.getBody()).getCategoryName());
        assertEquals(1,  categoryResponse.getProductList().size());
    }

    @Test
    public void test_categorynotFound(){

        int id = 103;
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        ResponseEntity<?> response= categoryService.getByCategory(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Category Not exists ",response.getBody());

    }

    @Test
    @Order(3)
    public void test_createCategory(){

        Category category=new Category(105,"car");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        ResponseEntity<String> response=categoryService.createCategory(category);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Category Created Successfully",response.getBody());
    }
}
