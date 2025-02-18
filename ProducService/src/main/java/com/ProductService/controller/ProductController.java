package com.ProductService.controller;


import com.ProductService.Interface.ProductInterface;
import com.ProductService.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/product")
@Tag(name = "Product Service", description = "Operation related to Product Service")
public class ProductController {

    @Autowired
    private ProductInterface productInterface;

    @Operation(summary = "Create new Product ",description ="Add a new Product" )
    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        return productInterface.createProduct(product);
    }

    @Operation(summary = "Get Products by Category Id ",description ="Retrieve all products belonging to specific category" )
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId){
        return productInterface.getProductsByCategory(categoryId);
    }
    @Operation(summary = "Get All Products ",description ="Retrieve list of all Products " )
    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return productInterface.getAllProducts();
    }

    @Operation(summary = "Get Products by Id", description = "Retrieve Details Of Specific Products ")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getByProductId(@PathVariable int productId){
        return productInterface.getByProductId(productId);
    }

    @Operation(summary = "Update a Products",description ="Modify an existing product by id " )
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateByProduct(@PathVariable int productId,@RequestBody Product product){
        return productInterface.updateByProductId(productId,product);

    }

    @Operation(summary = "Delete a Products",description ="Remove a product by id " )
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteByProduct(@PathVariable int productId){

        return productInterface.deleteByProductId(productId);

    }
    @Operation(summary = "Get Filters Records By Category Id and Products Price  ",description =" existing category id or filter each price " )

    @GetMapping("/filter")
    public ResponseEntity<?> getCategoryIdByProductPrice(@RequestParam( required = false) Integer categoryId, @RequestParam(required = false) Double productPrice){

        return productInterface.getCategoryByProductPrice(categoryId, productPrice);

    }
}














