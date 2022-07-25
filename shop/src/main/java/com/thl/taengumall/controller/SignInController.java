package com.thl.taengumall.controller;

import com.thl.taengumall.dto.SignInFields;
import com.thl.taengumall.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignInController {
    private final AccountService accountService;

    @GetMapping("/SignIn")
    public String signIn(Model model) {
        model.addAttribute("signInFields", new SignInFields());
        model.addAttribute("title", "로그인");
        return "/SignIn.html";
    }

    @PostMapping("/SignIn")
    public String signIn(SignInFields signInFields, Model model) {
        var account = accountService.getAccountByEmail(signInFields.getEmail());

        if (account != null) {
            if (account.getPassword().equals(signInFields.getPassword())) {
                return "redirect:/Index.html";
            } else {
                return "/SignIn.html";
            }
        } else {
            return "/SignIn.html";
        }
    }
}