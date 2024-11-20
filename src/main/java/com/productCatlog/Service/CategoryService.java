package com.productCatlog.Service;

import com.productCatlog.Entity.Category;
import com.productCatlog.Payload.CategoryDTO;
import com.productCatlog.Payload.ProductDTO;

import java.util.List;

public interface CategoryService {
    public Category createcategory(Category category);

    public List<CategoryDTO> getAllCategory(int pageNo,int pageSize);

    public CategoryDTO getProductByCategoryId(Long categoryId);
    public Category updateCategory(Category category ,Long categoryId);
    public void deleteCategory(Long categoryId);
}
