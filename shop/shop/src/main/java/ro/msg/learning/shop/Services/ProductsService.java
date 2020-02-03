package ro.msg.learning.shop.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ro.msg.learning.shop.DTO.ProductDto;
import ro.msg.learning.shop.Exceptions.ProductNotFoundException;
import ro.msg.learning.shop.Mapper.ProductMapper;
import ro.msg.learning.shop.Entities.Product;
import ro.msg.learning.shop.Entities.ProductCategory;
import ro.msg.learning.shop.Repositories.CategoryRepository;
import ro.msg.learning.shop.Repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> getProducts() {
        List<ProductDto> productList = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            productList.add(productMapper.mapProductToProductDto(p));
        }
        return productList;
    }

    public ProductCategory testCategory(String categoryName, String categoryDescription){
        ProductCategory newCategory = null;
        Optional<ProductCategory> categoryOptional = categoryRepository.findByName(categoryName);
        if (categoryOptional.isPresent()) {
            newCategory = categoryOptional.get();
        } else {
            newCategory = ProductCategory.builder()
                    .name(categoryName)
                    .description(categoryDescription)
                    .build();
            categoryRepository.save(newCategory);
        }
        return newCategory;
    }

    public ProductDto createProduct(String name, String description, BigDecimal price, Double weight, String imageUrl, String categoryName, String categoryDescription) {
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .weight(weight)
                .imageUrl(imageUrl)
                .category(this.testCategory(categoryName,categoryDescription))
                .build();
        productRepository.save(product);
        return productMapper.mapProductToProductDto(product);
    }

    public ProductDto getProductById(@PathVariable Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productMapper.mapProductToProductDto(productOptional.get());
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    public void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDto updateProduct(Integer id, String name, String description, BigDecimal price, Double weight, String imageUrl, String categoryName, String categoryDescription) {
        if (productRepository.findById(id).isPresent()) {
            Product existingProduct = productRepository.findById(id).get();

            existingProduct.setName(name);
            existingProduct.setPrice(price);
            existingProduct.setWeight(weight);
            existingProduct.setDescription(description);
            existingProduct.setImageUrl(imageUrl);
            existingProduct.setCategory(this.testCategory(categoryName,categoryDescription));

            Product updatedProduct = productRepository.save(existingProduct);

            return productMapper.mapProductToProductDto(updatedProduct);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

}
