package org.ecommerceapp.service.customer.cart;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.AddProductInCartDto;
import org.ecommerceapp.entity.CartItem;
import org.ecommerceapp.entity.Order;
import org.ecommerceapp.entity.Product;
import org.ecommerceapp.entity.User;
import org.ecommerceapp.enums.OrderStatus;
import org.ecommerceapp.repository.CartItemRepository;
import org.ecommerceapp.repository.OrderRepository;
import org.ecommerceapp.repository.ProductRepository;
import org.ecommerceapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements  CartService{

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;

  public ResponseEntity<?>addProductInCart(AddProductInCartDto addProductInCartDto) {
    Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),
        OrderStatus.PENDING);
    Optional<CartItem>cartItem = cartItemRepository.findByProductIdAndOrderIdAndUserId(
        addProductInCartDto.getProductId(), activeOrder.getId(), addProductInCartDto.getUserId());
    if(cartItem.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    Optional<Product> product = productRepository.findById(addProductInCartDto.getProductId());
    Optional<User> user = userRepository.findById(addProductInCartDto.getUserId());
    if(product.isPresent() && user.isPresent()) {
       CartItem cartItem1 = new CartItem();
       cartItem1.setProduct(product.get());
       cartItem1.setPrice(cartItem.get().getPrice());
       cartItem1.setQuantity(cartItem.get().getQuantity());
       cartItem1.setUser(user.get());
       cartItem1.setOrder(activeOrder);
       cartItemRepository.save(cartItem1);
       activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cartItem1.getPrice());
       activeOrder.setAmount(activeOrder.getAmount() + cartItem1.getPrice());
       activeOrder.getCartItemList().add(cartItem1);
       return ResponseEntity.status(HttpStatus.CREATED).body(cartItem1);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user or Product not found");
  }
}
