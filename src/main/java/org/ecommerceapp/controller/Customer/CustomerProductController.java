package org.ecommerceapp.controller.Customer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.ProductDto;
import org.ecommerceapp.service.admin.ProductService;
import org.ecommerceapp.service.customer.CustomerProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer/product")
public class CustomerProductController {

  private final CustomerProductService customerProductService;

  @GetMapping("")
  public List<ProductDto> getAllProducts() {
    return customerProductService.getAllProducts();
  }

  @GetMapping("/filter/{name}")
  public List<ProductDto> filterProducts(@PathVariable String name){
    return customerProductService.getFilterProducts(name);
  }
}
