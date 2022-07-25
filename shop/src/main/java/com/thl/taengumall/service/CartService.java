package com.thl.taengumall.service;

import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.jpa.Cart;
import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.repository.AccountRepository;
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
    @NotNull
    private final ProductRepository productRepository;

    public List<Product> getProductsByAccount(Account account) {
        var carts = repository.findByAccountId(account.getId());
        var products = new ArrayList<Product>();

        for (var c : carts)
            products.add(productRepository.findById(c.getId()));

        return products;
    }

    public void addCartByProduct(Account user, Product product) {
        var cart = new Cart();

        cart.setProductId(product.getId());
        cart.setAccountId(user.getId());
        cart.setCount(1);

        repository.save(cart);
    }
}
