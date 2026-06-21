package org.example.productcatalogservice.repositories;

import org.example.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Product getByName(String name);
    List<Product> findByName(String name);
}
