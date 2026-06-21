package org.example.productcatalogservice.repositories;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void TestFetchTypes(){
        Optional<Category> categoryOptional=categoryRepo.findById(1L);
        Category category=categoryOptional.get();
//        for(Product product:category.getProducts()){
//            System.out.println(product.getName());
//        }
    }
    @Test
    @Transactional
    public void TestNPlusOneProblem(){
       List<Category> categories= categoryRepo.findAll();
       for(Category c:categories){
           System.out.println(c.getName());
           for(Product p:c.getProducts()){
               System.out.println(p.getName());
           }
       }

    }
}