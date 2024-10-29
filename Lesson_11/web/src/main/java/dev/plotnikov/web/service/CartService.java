package dev.plotnikov.web.service;


import dev.plotnikov.web.client.CartClientApi;
import dev.plotnikov.web.models.CartDTO;
import dev.plotnikov.web.models.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.aspect.logging.Logging;
import ru.gb.aspect.recover.Recover;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    @Recover
    @Logging
    public boolean buyProduct(Long id) {
        if (ThreadLocalRandom.current().nextInt(1, 5) % 2 == 0) {
            throw new RuntimeException("Oops...error");
        }
        ResponseEntity<Void> response = cartClientApi.buyProduct(id);
        return response.getStatusCode().is2xxSuccessful();
    }
}
