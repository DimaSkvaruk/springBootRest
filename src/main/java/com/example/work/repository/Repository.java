package com.example.work.repository;

import com.example.work.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByIdAsc();

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findByBrand(String brand, Pageable pageable);

    Page<Product> findByFrom(String madeIn, Pageable pageable);


}
