package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.CartService;
import com.thl.taengumall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final String UPLOAD_DIR = "C:\\Users\\roach\\Desktop\\images\\";

    private final ProductService productService;
    private final AccountService accountService;
    private final CartService cartService;

    @GetMapping("/AddProduct")
    public String addProduct(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());

        model.addAttribute("username", user.getName());
        model.addAttribute("product", new Product());

        return "/AddProduct.html";
    }

    @PostMapping("/AddProduct")
    public String addProduct(Model model, Product product, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        product.setOwner(user.getId());
        productService.createProduct(product);
        return "redirect:/";
    }

    @PostMapping("/DoProduct")
    public String doProduct(Model model, Product product, Principal principal, @RequestParam(value = "action", required = true) String action) {
        var user = accountService.getAccountByEmail(principal.getName());

        if (action.equals("Buy")) {

        } else if (action.equals("AddCart")) {
            cartService.addCartByProduct(user, product);
        }

        return "redirect:/Carts";
    }

    @GetMapping("/product")
    public String index(int id, Model model, Principal principal) {
        var product = productService.getProductById(id);
        var user = accountService.getAccountByEmail(principal.getName());

        model.addAttribute("username", user.getName());
        model.addAttribute("product", product);

        return "/Product.html";
    }

    @GetMapping("/Products")
    public String products(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var products = productService.getProducts();

        model.addAttribute("username", user.getName());
        model.addAttribute("products", products);

        return "/Products.html";
    }

    @GetMapping("/productEdit")
    public String productEdit(int id, Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var product = productService.getProductById(id);

        model.addAttribute("username", user.getName());
        model.addAttribute("product", product);

        return "/ProductEdit.html";
    }

    @PostMapping("/ProductEdit")
    public String productEdit(Model model, Product product, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());

        product.setOwner(user.getId());
        productService.updateProduct(product);

        return "redirect:/";
    }

    @PostMapping("/Upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Principal principal) {
        var format = file.getOriginalFilename().split(".")[1];
        var user = accountService.getAccountByEmail(principal.getName());

        try {
            Path path = Paths.get(UPLOAD_DIR + user.getId() + "." + format);
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
