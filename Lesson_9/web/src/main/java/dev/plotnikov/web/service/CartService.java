package dev.plotnikov.web.service;


import dev.plotnikov.web.client.CartClientApi;
import dev.plotnikov.web.models.CartDTO;
import dev.plotnikov.web.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartClientApi cartClientApi;

    public List<CartDTO> getCart() {
        return cartClientApi.getCart();
    }

    public boolean addToCart(Order order) {
        ResponseEntity<Void> response = cartClientApi.addToCart(order);
        return response.getStatusCode().is2xxSuccessful();
    }

    public Long getCount() {
        return cartClientApi.getCount();
    }

    public boolean buyProduct(Long id) {
        ResponseEntity<Void> response = cartClientApi.buyProduct(id);
        return response.getStatusCode().is2xxSuccessful();
    }
}
