package dev.plotnikov.web.client;

import dev.plotnikov.web.models.CartDTO;
import dev.plotnikov.web.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "cart")
public interface CartClientApi {

    @PostMapping("/add")
    ResponseEntity<Void> addToCart(@RequestBody Order order);

    @GetMapping("/count")
    Long getCount();

    @GetMapping
    List<CartDTO> getCart();

    @GetMapping("/buy/{id}")
    ResponseEntity<Void> buyProduct(@PathVariable Long id);
}
