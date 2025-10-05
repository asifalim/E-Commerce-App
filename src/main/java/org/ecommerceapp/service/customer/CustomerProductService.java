package org.ecommerceapp.service.customer;

import java.util.List;
import org.ecommerceapp.dto.ProductDto;

public interface CustomerProductService {

  List<ProductDto> getAllProducts();
  List<ProductDto>getFilterProducts(String name);
}
