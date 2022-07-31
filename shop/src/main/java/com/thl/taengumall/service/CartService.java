package com.thl.taengumall.service;

import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.jpa.Cart;
import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.repository.CartRepository;
import com.thl.taengumall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    @NotNull
    private final CartRepository repository;

    public void addCart(Account user, Product product, int count) {
        var cart = new Cart();

        cart.setProductId(product.getId());
        cart.setAccountId(user.getId());
        cart.setCount(count);

        repository.save(cart);
    }

    public List<Cart> getCartsByAccountId(int id) {
        return repository.findByAccountId(id);
    }
}
