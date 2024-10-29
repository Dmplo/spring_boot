package dev.plotnikov.web.controllers;

import dev.plotnikov.web.models.Product;
import dev.plotnikov.web.service.CartService;
import dev.plotnikov.web.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService service;
    private final CartService cartService;

    @GetMapping("/")
    public String findAll(Model model) {
        List<Product> products = service.getProducts();
        Long count = cartService.getCount();

        model.addAttribute("products", products);
        model.addAttribute("count", count);
        return "index.html";
    }

}
