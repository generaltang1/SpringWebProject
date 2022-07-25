package com.thl.taengumall.repository;

import com.thl.taengumall.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByOwner(int id);
    Product findById(int id);
}