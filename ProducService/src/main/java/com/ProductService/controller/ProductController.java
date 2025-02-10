package com.ProductService.controller;


import com.ProductService.Interface.ProductInterface;
import com.ProductService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController

@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductInterface productInterface;

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Product product){

        return productInterface.createProduct(product);

    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId){

        return productInterface.getProductsByCategory(categoryId);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){

        return productInterface.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getByProductId(@PathVariable int productId){

        return productInterface.getByProductId(productId);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateByProduct(@PathVariable int productId,@RequestBody Product product){

        return productInterface.updateByProductId(productId,product);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteByProduct(@PathVariable int productId){

        return productInterface.deleteByProductId(productId);

    }
    @GetMapping("/filter")
    public ResponseEntity<?> getCategoryIdByProductPrice(@RequestParam( required = false) Integer categoryId, @RequestParam(required = false) Double productPrice){

        return productInterface.getCategoryByProductPrice(categoryId, productPrice);

    }
}














