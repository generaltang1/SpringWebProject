package com.thl.taengumall.controller;

import com.thl.taengumall.dto.SignUpFields;
import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AccountService accountService;

    @GetMapping("/Users")
    public String signUp(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var users = accountService.getAccounts();

        model.addAttribute("username", user.getName());
        model.addAttribute("users", users);

        return "/Users.html";
    }
}
