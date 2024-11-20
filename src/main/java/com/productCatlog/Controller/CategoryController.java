package com.productCatlog.Controller;

import com.productCatlog.Constant.Constant;
import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.CategoryDTO;
import com.productCatlog.Payload.ProductDTO;
import com.productCatlog.Service.CategoryService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category createcategory = categoryService.createcategory(category);
        return new ResponseEntity<>(createcategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory(
            @RequestParam(name="pageNo", defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize", defaultValue = "5",required = false)int pageSize
    ){
        List<CategoryDTO> allCategory = categoryService.getAllCategory(pageNo, pageSize);
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }

    @GetMapping("/{di}")
    public ResponseEntity<CategoryDTO> getAllProductByCategoryId(@PathVariable("di") Long categoryId){
        CategoryDTO product = categoryService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/{di}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable("di") Long categoryId){
        Category updateCategory = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{di}")
    public void deleteCategory(@PathVariable("di") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
