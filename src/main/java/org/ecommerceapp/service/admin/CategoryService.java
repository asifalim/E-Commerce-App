package org.ecommerceapp.service.admin;


import java.util.List;
import org.ecommerceapp.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

   List<CategoryDto> getAllCategories();
   CategoryDto CreateCategory(CategoryDto categoryDto);

}
