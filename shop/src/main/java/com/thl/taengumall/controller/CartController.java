package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.CartService;
import com.thl.taengumall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final AccountService accountService;
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/Carts")
    public String index(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var carts = cartService.getCartsByAccountId(user.getId());
        var products = new HashMap<Integer, Product>();

        for (var c : carts) {
            var product = productService.getProductById(c.getProductId());
            products.put(c.getId(), product);
        }

        model.addAttribute("products", products);
        model.addAttribute("carts", carts);
        model.addAttribute("username", user.getName());

        return "/Carts.html";
    }
}
