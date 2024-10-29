package dev.plotnikov.web.service;


import dev.plotnikov.web.client.StoreClientApi;
import dev.plotnikov.web.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final StoreClientApi storeClientApi;

    public List<Product> getProducts() {
        return storeClientApi.getAll();
    }

}
