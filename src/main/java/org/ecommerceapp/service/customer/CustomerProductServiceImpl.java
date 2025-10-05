package org.ecommerceapp.service.customer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.ProductDto;
import org.ecommerceapp.repository.ProductRepository;
import org.ecommerceapp.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDto> getAllProducts(){
    return productRepository.findAll().stream().map(productMapper::toDto).toList();
  }

  @Transactional(readOnly = true)
  public List<ProductDto> getFilterProducts(String name){
    return productRepository.findProductByName(name).stream().map(productMapper::toDto).toList();
  }

}
