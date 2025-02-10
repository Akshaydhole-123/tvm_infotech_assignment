package com.CategoryService;

import com.CategoryService.entity.Category;
import com.CategoryService.repository.CategoryRepository;
import com.CategoryService.service.CategoryService;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {


    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }



    @Test
    @Order(1)
    public void test_getAllCategory(){

        List<Category> mycategory=new ArrayList<>();
        mycategory.add(new Category(201,"Cast"));


        Mockito.when(categoryRepository.findAll()).thenReturn(mycategory);


        ResponseEntity<List<Category>> response=  categoryService.getAllCategory();

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());

        assertEquals(1,response.getBody().size());


    }
    @Test
    @Order(2)
    public void test_getByCategory(){
        Integer id=104;
        Category mycate=new Category(104,"phone") ;



        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(mycate));


        ResponseEntity<?> response= categoryService.getByCategory(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mycate,response.getBody());


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
