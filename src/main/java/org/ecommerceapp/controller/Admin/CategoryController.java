package org.ecommerceapp.controller.Admin;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.CategoryDto;
import org.ecommerceapp.service.admin.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/category")
public class CategoryController {
   private final CategoryService categoryService;

   @GetMapping("")
   public List<CategoryDto> getAllCategories() {
     return categoryService.getAllCategories();
   }
   @PostMapping("")
   public CategoryDto CreateCategory(@RequestBody CategoryDto categoryDto) {
      return categoryService.CreateCategory(categoryDto);
   }
}
