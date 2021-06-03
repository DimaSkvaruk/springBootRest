package com.example.work.controller;

import com.example.work.service.ProductService;
import com.example.work.loging.Loggable;
import com.example.work.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    //api/products?page=0&sort=price&order=asc
    @Loggable
    @GetMapping("/products")
    public Page<Product> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String order) {
        return productService.getAll(page, sort, order);
    }

    //api/products/1
    @Loggable
    @GetMapping("/products/{id}")
    public Product getById(@PathVariable(name = "id") Long id) {
        return productService.getById(id);
    }

    @Loggable
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    //api/products/1
    @Loggable
    @PutMapping("/products/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    //api/products/1
    @Loggable
    @DeleteMapping("/products/{id}")
    public Optional<Product> deleteById(@PathVariable(name = "id") Long id) {
        return productService.deleteById(id);
    }



}
