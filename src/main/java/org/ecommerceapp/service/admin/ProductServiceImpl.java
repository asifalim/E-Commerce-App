package org.ecommerceapp.service.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.ProductDto;
import org.ecommerceapp.entity.Product;
import org.ecommerceapp.repository.ProductRepository;
import org.ecommerceapp.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDto> getAllProducts(){
    return productRepository.findAll().stream().map(productMapper::toDto).toList();
  }

  public ProductDto getProduct(Long id){
    return productMapper.toDto(productRepository.findById(id).get());
  }

  @Transactional(readOnly = true)
  public List<ProductDto> getFilterProducts(String name){
    return productRepository.findProductByName(name).stream().map(productMapper::toDto).toList();
  }

  public ProductDto addProduct(ProductDto productDto) throws IOException {
       return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
  }

  public  ProductDto updateProduct(Long id, ProductDto productDto) throws IOException {
      Optional<Product> productOptional = productRepository.findById(id);
      if(productOptional.isEmpty()) {
        return null;
      }
      return productMapper.toDto(productRepository.save(productMapper.toEntity(productDto)));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

}
