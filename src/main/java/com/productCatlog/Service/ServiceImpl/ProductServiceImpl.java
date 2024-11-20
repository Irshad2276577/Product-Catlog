package com.productCatlog.Service.ServiceImpl;

import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.ProductDTO;
import com.productCatlog.Repository.CategoryRepository;
import com.productCatlog.Repository.ProductRepository;
import com.productCatlog.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        ProductDTO dto=new ProductDTO();
        dto.setProductId(savedProduct.getProductId());
        dto.setName(savedProduct.getName());
        dto.setPrice(savedProduct.getPrice());
        dto.setCategoryId(savedProduct.getCategory().getCategoryId());
        return dto;
    }

    @Override
    public List<Product> getAllProduct(int pageNo,int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.getContent();
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        Optional<Product> products = productRepository.findById(productId);
        if(products.isPresent()){
            Product newProduct = products.get();
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            return productRepository.save(newProduct);
        }
        return null;
    }
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
