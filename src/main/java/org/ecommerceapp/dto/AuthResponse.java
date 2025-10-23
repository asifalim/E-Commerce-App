package org.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.ecommerceapp.enums.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
  private Long id;
  private String token;
  private String message;
  private String email;
  private UserRole role;
}
