package ro.msg.learning.shop.DTO;


import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductDto {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private String categoryName;
    private String categoryDescription;
}
