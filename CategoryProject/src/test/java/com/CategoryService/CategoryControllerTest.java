package com.CategoryService;


import com.CategoryService.Interfase.CategoryInterfase;
import com.CategoryService.controller.CategoryController;
import com.CategoryService.entity.Category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.junit.jupiter.api.Order;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@WebMvcTest(CategoryController.class)

public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @MockitoBean
    private  CategoryInterfase categoryInterfase;

    @Test
    @Order(1)
    public void test_getAllCategory()throws Exception{

        List<Category> categories= List.of(new Category(202,"Pass"));

        when(categoryInterfase.getAllCategory()).thenReturn((ResponseEntity<List<Category>>) categories);

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Pass"));

    }

    @Test
    @Order(2)
    public void test_createCategory() throws Exception{

        Category categories=new  Category(205,"Pass");

        Mockito.when(categoryInterfase.createCategory(any(Category.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).<String>body(String.valueOf(categories)));

        mockMvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(categories)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.categoryName").value("Pass"));

    }



}
