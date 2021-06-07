package com.example.work.service;

import com.example.work.model.Product;
import com.example.work.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    Repository repository;

    @Override
    public Product getById(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public Optional<Product> deleteById(Long id) {
        Optional<Product> byId = repository.findById(id);
        byId.ifPresent(product -> repository.delete(product));
        return byId;
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }


    @Override
    public Page<Product> getAll(int pageNum, String sortBy, String order) {
        Pageable page = PageRequest.of(pageNum, 3, order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        return repository.findAll(page);
    }

    @Override
    public Product update(Long id, Product product) {
        Product byId = repository.findById(id).get();
        byId.setBrand(product.getBrand());
        byId.setFrom(product.getFrom());
        byId.setName(product.getName());
        byId.setPrice(product.getPrice());
        return byId;
    }

}