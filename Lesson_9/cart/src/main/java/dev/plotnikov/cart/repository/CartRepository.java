package dev.plotnikov.cart.repository;

import dev.plotnikov.cart.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select count (distinct c.product_id) from Cart c")
    Long getCartCount();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from Cart c WHERE c.product_id = :id")
    void buyProduct(Long id);
}
