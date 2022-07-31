package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.ProductService;
import com.thl.taengumall.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class RecordController {
    private final AccountService accountService;
    private final RecordService recordService;
    private final ProductService productService;

    @GetMapping("/Records")
    public String index(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var records = recordService.getRecordsByAccountId(user.getId());
        var products = new HashMap<Integer, Product>();

        for (var r : records) {
            var product = productService.getProductById(r.getProductId());
            products.put(r.getId(), product);
        }

        model.addAttribute("username", user.getName());
        model.addAttribute("records", records);
        model.addAttribute("products", products);

        return "/Records.html";
    }
}
