package org.ecommerceapp.service.customer.cart;

import lombok.RequiredArgsConstructor;
import org.ecommerceapp.repository.CartItemRepository;
import org.ecommerceapp.repository.OrderRepository;
import org.ecommerceapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements  CartService{

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CartItemRepository cartItemRepository;

}
