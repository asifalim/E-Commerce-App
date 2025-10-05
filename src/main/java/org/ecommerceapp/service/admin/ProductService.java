package org.ecommerceapp.service.admin;

import java.io.IOException;
import java.util.List;
import org.ecommerceapp.dto.ProductDto;

public interface ProductService {
  List<ProductDto> getAllProducts();
  ProductDto getProduct(Long id);
  List<ProductDto>getFilterProducts(String name);
  ProductDto addProduct(ProductDto productDto) throws IOException;
  ProductDto updateProduct(Long id, ProductDto productDto) throws IOException;
  void deleteProduct(Long id);
}
