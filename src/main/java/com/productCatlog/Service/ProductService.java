package com.productCatlog.Service;

import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.ProductDTO;
import java.util.List;

public interface ProductService {
    public ProductDTO createProduct(ProductDTO product);
    public List<Product> getAllProduct(int pageNo,int pageSize);

    public Product getProductById(Long productId);

    public Product updateProduct(Product product ,Long productId);
    public void deleteProduct(Long productId);


}
