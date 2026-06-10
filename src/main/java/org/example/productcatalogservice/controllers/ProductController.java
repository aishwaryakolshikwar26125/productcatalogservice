package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    //APIs

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{ID}")
    public ProductDto getProductById(@PathVariable("ID") Long productId) {
        return null;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto input) {
        return input;
    }

    public void sayHello(){

    }
}
