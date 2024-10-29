package dev.plotnikov.store.repositories;

import dev.plotnikov.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
