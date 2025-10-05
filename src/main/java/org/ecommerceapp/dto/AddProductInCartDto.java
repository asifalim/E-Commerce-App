package org.ecommerceapp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddProductInCartDto {

  private Long userId;
  private Long productId;
}
