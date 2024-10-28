package dev.plotnikov.store;

import dev.plotnikov.store.models.Product;
import dev.plotnikov.store.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(ProductService productService) {
//        return args -> {
//            productService.save(new Product(null, "Philips DST8020/20", 10000.00));
//            productService.save(new Product(null, "REDMOND RI-C278", 2500.00));
//            productService.save(new Product(null, "BRAUN SI5017GR", 5500.00));
//        };
//    }
}

