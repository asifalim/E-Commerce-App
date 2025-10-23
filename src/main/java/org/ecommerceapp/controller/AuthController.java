package org.ecommerceapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.service.JwtService;
import org.ecommerceapp.dto.LoginRequest;
import org.ecommerceapp.dto.AuthResponse;
import org.ecommerceapp.dto.RegisterRequest;
import org.ecommerceapp.entity.User;
import org.ecommerceapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final JwtService jwtService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    User user = userService.register(request);

    UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
    String token = jwtService.generateToken(userDetails);

    AuthResponse response = new AuthResponse(
        user.getId(),
        token,
        "Registration successful",
        user.getEmail(),
        user.getRole()
    );

    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    User user = userService.authenticate(request.getEmail(), request.getPassword());

    UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
    String token = jwtService.generateToken(userDetails);

    AuthResponse response = new AuthResponse(
        user.getId(),
        token,
        "Login successful",
        user.getEmail(),
        user.getRole()
    );

    return ResponseEntity.ok(response);
  }

}
