package org.ecommerceapp.controller.Customer;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.AddProductInCartDto;
import org.ecommerceapp.service.customer.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/cart")
public class CartController {

  private final CartService cartService;

  @PostMapping("")
  ResponseEntity<?> addProductInCart(@RequestBody AddProductInCartDto addProductInCartDto) {
    System.out.println("DTO Received: " + addProductInCartDto);
    return cartService.addProductInCart(addProductInCartDto);
  }
}
