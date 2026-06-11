package org.example.productcatalogservice.controllers;

import org.example.productcatalogservice.dtos.CategoryDto;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDto> getProductById(@PathVariable("ID") Long productId) {
        Product product=productService.getProductById(productId);
        if(productId<=0l){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        if(product!=null){
            ProductDto pd=from(product);
            return new ResponseEntity<>(pd, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/products/{id}")
    public  ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
            Product product=productService.replaceProduct(from(productDto),id);
            if(product!=null){
                return new ResponseEntity<>(from(product),HttpStatus.OK);
            }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

    }
    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto input) {
        return input;
    }

    private ProductDto from(Product product){
    ProductDto pd=new ProductDto();
    pd.setName(product.getName());
    pd.setId(product.getId());
        Category c=product.getCategory();
        if(c!=null){
        CategoryDto cd=new CategoryDto();
        cd.setName(c.getName());
        cd.setDescription(c.getDescription());
        cd.setId(c.getId());
            pd.setCategory(cd);
        }

    pd.setPrice(product.getPrice());
    pd.setDescription(product.getDescription());
    pd.setImageUrl(product.getImageUrl());

    return pd;
    }
//    public Product from(ProductDto pd){
//        Product p=new Product();
//        p.setName(pd.getName());
//        p.setId(pd.getId());
//
//        if(pd.getCategory()!=null){
//            CategoryDto cd=pd.getCategory();
//            Category c=new Category();
//            c.setName(cd.getName());
//            c.setDescription(cd.getDescription());
//            c.setId(cd.getId());
//            p.setCategory(c);
//        }
//        p.setPrice(pd.getPrice());
//        p.setDescription(pd.getDescription());
//        p.setImageUrl(pd.getImageUrl());
//        return p;
//    }
    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
}
