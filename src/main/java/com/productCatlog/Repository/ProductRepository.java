package com.productCatlog.Repository;

import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory(Category category);

}
