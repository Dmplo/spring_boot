package dev.plotnikov.cart.client;

import dev.plotnikov.cart.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product")
public interface ProductClientApi {

    @GetMapping(value = "/{id}")
    ResponseEntity<Optional<Product>> getOne(@PathVariable Long id);
}


