package com.sachin.ecom_proj.service;

import com.sachin.ecom_proj.model.Product;
import com.sachin.ecom_proj.repo.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    //we dont want service to talk to db
    //we also dont want to return hardcoded values

    private ProductRepo repo;
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }
    public List<Product> getAllProducts() {
    return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(Integer id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return repo.save(product);
    }


    public void deleteProduct(Integer id) {

        repo.deleteById(id);
    }


    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);

    }
}
