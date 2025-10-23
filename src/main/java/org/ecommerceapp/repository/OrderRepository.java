package org.ecommerceapp.repository;

import org.ecommerceapp.entity.Order;
import org.ecommerceapp.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
