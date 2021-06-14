package com.example.work.service;

import com.example.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Optional<Product> deleteById(Long id);

    Product getById(Long id);

    Page<Product> getAll(int pageNum, String sortBy, String order);

    Optional<Product> update(Long id, Product product);

    Page<Product> find(Specification<Product> specification, Integer page, String sort, String order);

}
