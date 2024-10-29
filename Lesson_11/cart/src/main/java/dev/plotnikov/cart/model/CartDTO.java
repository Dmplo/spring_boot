package dev.plotnikov.cart.model;

import lombok.Data;

@Data
public class CartDTO {
    private Product product;
    private int amount;

}
