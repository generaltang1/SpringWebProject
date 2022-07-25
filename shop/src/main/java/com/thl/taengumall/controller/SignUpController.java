package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Account;
import lombok.RequiredArgsConstructor;
import com.thl.taengumall.dto.SignUpFields;
import com.thl.taengumall.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final AccountService accountService;

    @GetMapping("/SignUp")
    public String signUp(Model model) {
        model.addAttribute("signUpFields", new SignUpFields());
        model.addAttribute("title", "회원 가입");
        return "/SignUp.html";
    }

    @PostMapping("/SignUp")
    public String signUp(SignUpFields signUp, Model model) {
        var account = new Account();
        account.setName(signUp.getName());
        account.setAddress(signUp.getAddress());
        account.setPassword(signUp.getPassword());
        account.setEmail(signUp.getEmail());

        try {
            accountService.createAccount(account);
            return "redirect:/SignIn.html";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "/SignUp.html";
        }
    }
}
