package org.ecommerceapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.ecommerceapp.enums.OrderStatus;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String orderDescription;

  private LocalDate date;

  private Long amount;

  private String address;

  private String payment;

  private OrderStatus orderStatus;

  private Long totalAmount;

  private Long discount;

  private UUID trackingId;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
  private List<CartItem> cartItemList;
}
