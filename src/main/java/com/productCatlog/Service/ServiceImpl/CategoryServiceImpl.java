package com.productCatlog.Service.ServiceImpl;

import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.CategoryDTO;
import com.productCatlog.Payload.ProductDTO;
import com.productCatlog.Repository.CategoryRepository;
import com.productCatlog.Repository.ProductRepository;
import com.productCatlog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Category createcategory(Category category){
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }


    @Override
    public List<CategoryDTO> getAllCategory(int pageNo,int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Category> category = categoryRepository.findAll(pageRequest);

        List<Category> content = category.getContent();

        List<CategoryDTO> collects = content.stream().map(categories -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryId(categories.getCategoryId());
            dto.setName(categories.getName());

            List<Product> products = productRepository.findAllByCategory(categories);
            List<ProductDTO> collect = products.stream().map(product -> {
                ProductDTO dtos = new ProductDTO();
                dtos.setProductId(product.getProductId());
                dtos.setName(product.getName());
                dtos.setPrice(product.getPrice());
                return dtos;
            }).collect(Collectors.toList());
            dto.setProducts(collect);
            return dto;
        }).collect(Collectors.toList());
        return collects;
    }
    @Override
    public CategoryDTO getProductByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category Id not found"));
        List<Product> products = productRepository.findAllByCategory(category);
        List<ProductDTO> collect = products.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setProductId(product.getProductId());
            dto.setPrice(product.getPrice());
            dto.setName(product.getName());
            dto.setCategoryId(category.getCategoryId());
            return dto;
        }).collect(Collectors.toList());
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(collect);
        return categoryDTO;
    }


    @Override
    public Category updateCategory(Category category ,Long categoryId){
        Optional<Category> categoryid = categoryRepository.findById(categoryId);
        if(categoryid.isPresent()){
            Category newCategory = categoryid.get();
            newCategory.setName(category.getName());
           return categoryRepository.save(newCategory);
        }
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
