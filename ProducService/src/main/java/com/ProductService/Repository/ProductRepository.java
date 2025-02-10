package com.ProductService.Repository;

import com.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findByCategoryIdAndProductPrice(Integer categoryId,Double productPrice);
    List<Product> findByProductPrice(Double productPrice);
}
