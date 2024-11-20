package com.productCatlog.Repository;

import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
