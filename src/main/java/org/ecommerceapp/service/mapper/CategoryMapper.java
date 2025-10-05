package org.ecommerceapp.service.mapper;

import org.ecommerceapp.dto.CategoryDto;
import org.ecommerceapp.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDto toDto(Category category);
  Category toEntity(CategoryDto categoryDto);
}
