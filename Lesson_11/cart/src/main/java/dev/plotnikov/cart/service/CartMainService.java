package dev.plotnikov.cart.service;

import dev.plotnikov.cart.client.ProductClientApi;
import dev.plotnikov.cart.model.Cart;
import dev.plotnikov.cart.model.CartDTO;
import dev.plotnikov.cart.model.Product;
import dev.plotnikov.cart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CartMainService {

    private final CartRepository repository;
    private final ProductClientApi productClientApi;

    public Cart add(Cart cart) {
        return repository.save(cart);
    }

    public Long getCount() {
        return repository.getCartCount();
    }

    public List<CartDTO> findAll() {
        List<Cart> list = repository.findAll();
        Map<Long, CartDTO> cartDTOMap = new HashMap<>();

        for (Cart cart : list) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setAmount(cart.getAmount());
            Product product = getProduct(cart.getProduct_id());
            cartDTO.setProduct(product);

            if (cartDTOMap.containsKey(cartDTO.getProduct().id())) {
                CartDTO cartDTO1 = cartDTOMap.get(cartDTO.getProduct().id());
                cartDTO1.setAmount(cartDTO1.getAmount() + cartDTO.getAmount());
            } else {
                cartDTOMap.put(cartDTO.getProduct().id(), cartDTO);
            }
        }
        List<CartDTO> cartDTOList = new ArrayList<>(cartDTOMap.values());
        return cartDTOList;
    }

    private Product getProduct(Long id) {
        ResponseEntity<Optional<Product>> response = productClientApi.getOne(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            if (Objects.requireNonNull(response.getBody()).isPresent()) {
                return response.getBody().get();
            }
            throw new NoSuchElementException("Продукт с id " + id + " не найден");
        }
        throw new NoSuchElementException("Продукт с id " + id + " не найден");
    }

    public void buyProduct(Long id) {
       repository.buyProduct(id);
    }
}

