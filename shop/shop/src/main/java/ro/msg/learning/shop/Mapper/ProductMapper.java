package ro.msg.learning.shop.Mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.ProductDto;
import ro.msg.learning.shop.Entities.Product;

@Component
public class ProductMapper {
    public ProductDto mapProductToProductDto (Product product){
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .weight(product.getWeight())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

//    public List<ProductDto> mapProductListToProductDtoList(List<Product> productList){
//         List<ProductDto> productDtoList = productList.stream().map(this::mapProductToProductDto).collect(Collectors.toList());
//         return productDtoList;
//    }
}
