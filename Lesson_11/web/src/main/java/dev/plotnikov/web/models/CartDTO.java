package dev.plotnikov.web.models;

import lombok.Data;

@Data
public class CartDTO {
    private Product product;
    private int amount;

}
