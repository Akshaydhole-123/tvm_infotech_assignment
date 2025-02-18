package com.CategoryService.Interface;


import com.CategoryService.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:8082",value = "Product-Client")
@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {


    @GetMapping("/product/category/{categoryId}")

    List<Product> getProductsByCategory(@PathVariable int categoryId);

}
