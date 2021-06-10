package com.example.work.service;

import com.example.work.exceptions.NotFoundException;
import com.example.work.model.Product;
import com.example.work.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Data not found with id: " + id));
    }


    @Override
    public Optional<Product> deleteById(Long id) {
        Optional<Product> byId = repository.findById(id);
        byId.orElseThrow(() -> new NotFoundException("Data not found with id: " + id));
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
        byId.setMadeIn(product.getMadeIn());
        byId.setModel(product.getModel());
        byId.setPrice(product.getPrice());
        repository.save(byId);
        return byId;
    }

    @Override
    public Page<Product> find(Specification<Product> spec, Integer pageNum, String sort, String order) {
        Pageable page = PageRequest.of(pageNum, 3, order.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending());
        return repository.findAll(spec, page);
    }
}