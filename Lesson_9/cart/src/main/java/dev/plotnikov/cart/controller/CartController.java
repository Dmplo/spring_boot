package dev.plotnikov.cart.controller;

import dev.plotnikov.cart.model.Cart;
import dev.plotnikov.cart.model.CartDTO;
import dev.plotnikov.cart.service.CartMainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartMainService service;

    @GetMapping
    public List<CartDTO> getCart() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody Cart cart) {
        service.add(cart);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/buy/{id}")
   public ResponseEntity<Void> buyProduct(@PathVariable Long id) {
        service.buyProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        return ResponseEntity.ok().body(service.getCount());
    }
}
