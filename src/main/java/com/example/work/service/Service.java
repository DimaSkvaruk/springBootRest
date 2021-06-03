package com.example.work.service;

import com.example.work.model.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface Service {

    Product saveProduct(Product product);

    Optional<Product> deleteById(Long id);

    Product getById(Long id);

    Page<Product> getAll(int pageNum, String sortBy, String order);

    Product update(Long id, Product product);

}
