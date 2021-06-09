package com.example.work.controller;

import com.example.work.service.ProductService;
import com.example.work.loging.Loggable;
import com.example.work.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    //api/products?page=0&sort=price&order=desc
    @Loggable
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll(page, sort, order));
    }

    //api/products/1
    @Loggable
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    //api/products
    @Loggable
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    //api/products/1
    @Loggable
    @PutMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    //api/products/1
    @Loggable
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> deleteById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }

    //api/products/find?brand=iPhone&page=0&sort=price&order=desc
    @Loggable
    @GetMapping("/products/find")
    public ResponseEntity<Page<Product>> getByBrand(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(productService.findByBrand(brand, page, sort, order));
    }


}
