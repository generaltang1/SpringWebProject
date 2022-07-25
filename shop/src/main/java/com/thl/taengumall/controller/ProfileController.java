package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Account;
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
public class ProfileController {
    private final AccountService accountService;

    @GetMapping("/Profile")
    public String addProduct(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());

        model.addAttribute("username", user.getName());
        model.addAttribute("user", user);

        return "/Profile.html";
    }

    @PostMapping("/Profile")
    public String addProduct(Model model, Account user, Principal principal) {
        var old = accountService.getAccountByEmail(principal.getName());

        model.addAttribute("username", old.getName());

        old.setName(user.getName());
        old.setPassword(user.getPassword());
        old.setAddress(user.getAddress());

        accountService.updateAccount(old);

        model.addAttribute("user", old);

        return "redirect:/Profile";
    }
}
