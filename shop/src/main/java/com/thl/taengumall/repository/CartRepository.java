package com.thl.taengumall.repository;

import com.thl.taengumall.jpa.Cart;
import com.thl.taengumall.jpa.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByAccountId(int id);
}
