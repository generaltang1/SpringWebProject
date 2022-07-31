package com.thl.taengumall.controller;

import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.jpa.Record;
import com.thl.taengumall.service.AccountService;
import com.thl.taengumall.service.CartService;
import com.thl.taengumall.service.ProductService;
import com.thl.taengumall.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final String UPLOAD_DIR = "C:\\Users\\roach\\Desktop\\images\\";

    private final ProductService productService;
    private final AccountService accountService;
    private final RecordService recordService;
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

    @GetMapping("/product")
    public String index(int id, Model model, Principal principal) {
        var product = productService.getProductById(id);
        var user = accountService.getAccountByEmail(principal.getName());
        var count = Integer.valueOf(1);

        model.addAttribute("username", user.getName());
        model.addAttribute("product", product);
        model.addAttribute("count", count);

        return "/Product.html";
    }

    @PostMapping("/DoProduct")
    public String doProduct(Model model, Product product, Integer count, Principal principal, @RequestParam(value = "action", required = true) String action) {
        var user = accountService.getAccountByEmail(principal.getName());

        if (action.equals("Buy")) {
            var record = new Record();
            recordService.addRecord(user, product, count);
            return "redirect:/Records";
        } else if (action.equals("AddCart")) {
            cartService.addCart(user, product, count);
            return "redirect:/Carts";
        }

        return "redirect:/";
    }

    @GetMapping("/Products")
    public String products(Model model, Principal principal) {
        var user = accountService.getAccountByEmail(principal.getName());
        var products = productService.getProducts();

        model.addAttribute("username", user.getName());
        model.addAttribute("products", products);

        return "/Products.html";
    }

    @GetMapping("/ProductEdit")
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
    public String uploadFile(@RequestParam("file") MultipartFile file, int id, RedirectAttributes attributes, Principal principal) {
        try {
            Path path = Paths.get(UPLOAD_DIR + id + ".jpg");
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping(value="/ProductImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> GetImage(int id) throws IOException {
        var file = new File(UPLOAD_DIR + id + ".jpg");

        if (file.exists()) {
            var stream = new FileInputStream(file);
            var bytes = stream.readAllBytes();
            stream.close();

            return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
        }

        var stream = new FileInputStream(UPLOAD_DIR + "empty.png");
        var bytes = stream.readAllBytes();
        stream.close();

        return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
    }
}
