package org.ecommerceapp.repository;

import java.util.Optional;
import org.ecommerceapp.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductIdAndOrderIdAndUserId(Long ProductId, Long OrderId, Long userId);
}
