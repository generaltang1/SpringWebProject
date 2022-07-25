package com.thl.taengumall.service;

import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    @NotNull
    private final ProductRepository repository;

    public List<Product> getProducts() {
        var products = repository.findAll(Sort.by(Sort.Direction.DESC, "created"));
        return products;
    }

    public List<Product> getProductsByAccount(Account account) {
        var products = repository.findByOwner(account.getId());
        return products;
    }

    public Product getProductById(int id) {
        var product = repository.findById(id);
        return product;
    }

    public void updateProduct(Product product) {
        var p = repository.findById(product.getId());
        p.setDetail(product.getDetail());
        p.setPrice(product.getPrice());
        p.setStock(product.getStock());
    }

    public void createProduct(Product product) {
        repository.save(product);
    }
}
