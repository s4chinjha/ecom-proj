package com.sachin.ecom_proj.controller;

import com.sachin.ecom_proj.model.Product;
import com.sachin.ecom_proj.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    //after creating the service
    //now  we create a reference

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    //to get all the products
    //we use ResponseEntity to return our oen status code
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        //we dont directly talk to the database we use a service
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.FOUND);
    }

    //to get one product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        Product product = service.getProductById(id);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        //send to service layer
        //product1 to check if it was added correctly

        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/products/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }
}
