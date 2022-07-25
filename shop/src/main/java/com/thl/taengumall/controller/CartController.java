package com.thl.taengumall.controller;

import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final AccountService accountService;
    private final CartService cartService;

    @GetMapping("/Carts")
    public String index(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var products = cartService.getProductsByAccount(user);

        model.addAttribute("products", products);
        model.addAttribute("username", user.getName());

        return "/Carts.html";
    }
}
