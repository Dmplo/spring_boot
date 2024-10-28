package dev.plotnikov.store.controllers;

import dev.plotnikov.store.models.Product;
import dev.plotnikov.store.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> findAll() {
        return service.getProducts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Product>> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

}
