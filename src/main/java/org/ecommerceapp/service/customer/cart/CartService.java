package org.ecommerceapp.service.customer.cart;

import org.ecommerceapp.dto.AddProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
  ResponseEntity<?> addProductInCart(AddProductInCartDto addProductInCartDto);
}
