package org.ecommerceapp.service;

import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.RegisterRequest;
import org.ecommerceapp.entity.Order;
import org.ecommerceapp.entity.User;
import org.ecommerceapp.enums.OrderStatus;
import org.ecommerceapp.enums.UserRole;
import org.ecommerceapp.repository.CartItemRepository;
import org.ecommerceapp.repository.OrderRepository;
import org.ecommerceapp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final OrderRepository orderRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(getAuthorities(user.getRole()))
        .disabled(!user.getEnabled())
        .build();
  }

  private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  public User register(RegisterRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new RuntimeException("User already exists with email: " + request.getEmail());
    }

    if (request.getRole() == UserRole.ADMIN || request.getRole() == UserRole.SUPERADMIN) {
      throw new RuntimeException("Cannot register with admin roles");
    }

    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setName(request.getName());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setRole(request.getRole());
    user.setEnabled(true);

    Order order = new Order();
    order.setAmount(0L);
    order.setTotalAmount(0L);
    order.setDiscount(0L);
    order.setUser(user);
    order.setOrderStatus(OrderStatus.PENDING);
    orderRepository.save(order);

    return userRepository.save(user);
  }

  public User authenticate(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    if (!user.getEnabled()) {
      throw new RuntimeException("Account is disabled");
    }

    return user;
  }
}