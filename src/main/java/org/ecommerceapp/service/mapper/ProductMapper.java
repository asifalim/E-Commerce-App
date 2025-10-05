package org.ecommerceapp.service.mapper;

import org.ecommerceapp.dto.ProductDto;
import org.ecommerceapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "category.id", target = "categoryId")
  @Mapping(target = "img", ignore = true)
  @Mapping(source = "img", target = "byteImg")
  ProductDto toDto(Product product);

  @Mapping(source = "categoryId", target = "category.id")
  @Mapping(target = "img", expression = "java(productDto.getImg() != null && !productDto.getImg().isEmpty() ? productDto.getImg().getBytes() : null)")
  Product toEntity(ProductDto productDto) throws java.io.IOException;

}
