package org.ecommerceapp.controller.Admin;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ecommerceapp.dto.ProductDto;
import org.ecommerceapp.service.admin.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("")
  public List<ProductDto> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/filter/{name}")
  public List<ProductDto> filterProducts(@PathVariable String name){
    return productService.getFilterProducts(name);
  }

  @GetMapping("/{id}")
  public ProductDto getProduct(@PathVariable Long id){
    return productService.getProduct(id);
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ProductDto addProduct(@ModelAttribute ProductDto productDto) throws IOException {
    return productService.addProduct(productDto);
  }

  @PutMapping("/{id}")
  public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws IOException {
     return productService.updateProduct(id, productDto);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
     productService.deleteProduct(id);
  }


}
