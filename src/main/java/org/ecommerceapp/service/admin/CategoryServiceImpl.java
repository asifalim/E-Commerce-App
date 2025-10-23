package org.ecommerceapp.service.admin;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.CategoryDto;
import org.ecommerceapp.repository.CategoryRepository;
import org.ecommerceapp.service.mapper.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryDto> getAllCategories() {
     return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
  }

  public CategoryDto CreateCategory(CategoryDto categoryDto) {
      return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
  }
}
