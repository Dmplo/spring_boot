package dev.plotnikov.web.client;

import dev.plotnikov.web.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "store")
public interface StoreClientApi {

    @GetMapping()
    List<Product> getAll();

}
