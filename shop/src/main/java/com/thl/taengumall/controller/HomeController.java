package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final AccountService accountService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var products = productService.getProducts();

        model.addAttribute("products", products);
        model.addAttribute("username", user.getName());

        return "/index.html";
    }

    @GetMapping("/About")
    public String about(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());

        model.addAttribute("username", user.getName());

        return "/About.html";
    }
}
