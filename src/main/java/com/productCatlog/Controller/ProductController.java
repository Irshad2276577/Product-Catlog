package com.productCatlog.Controller;

import com.productCatlog.Constant.Constant;
import com.productCatlog.Entity.Category;
import com.productCatlog.Entity.Product;
import com.productCatlog.Payload.ProductDTO;
import com.productCatlog.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product){
        ProductDTO product1 = productService.createProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(
            @RequestParam(name="pageNo", defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize", defaultValue = "5",required = false)int pageSize)
            {
                List<Product> product = productService.getAllProduct(pageNo, pageSize);
                return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{di}")
    public ResponseEntity<Product> getProductsByCategory(@PathVariable("di")Long productId) {
        Product products = productService.getProductById(productId);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PutMapping("/{di}")
    public ResponseEntity<Product> updateCategory(@RequestBody Product product,@PathVariable("di") Long productId){
        Product products = productService.updateProduct(product, productId);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @DeleteMapping("/{di}")
    public void productCategory(@PathVariable("di") Long productId){
        productService.deleteProduct(productId);
    }
}
