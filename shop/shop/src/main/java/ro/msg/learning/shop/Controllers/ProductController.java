package ro.msg.learning.shop.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductDto;
import ro.msg.learning.shop.Services.ProductsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductsService productsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
   public List<ProductDto> getAllProducts(){
        return productsService.getProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable Integer id) {
        return productsService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productsService.createProduct(productDto.getName(), productDto.getDescription(),productDto.getPrice(), productDto.getWeight(), productDto.getImageUrl(), productDto.getCategoryName(), productDto.getCategoryDescription());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) {
        productsService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@RequestBody ProductDto productDTO, @PathVariable Integer id) {
        return productsService.updateProduct(id, productDTO.getName(),productDTO.getDescription(), productDTO.getPrice(), productDTO.getWeight(),productDTO.getImageUrl(), productDTO.getCategoryName(), productDTO.getCategoryDescription());
    }


}
