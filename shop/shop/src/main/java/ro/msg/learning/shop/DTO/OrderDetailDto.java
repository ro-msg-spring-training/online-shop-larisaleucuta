package ro.msg.learning.shop.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderDetailDto {
    private Integer productId;
    private Integer quantity;
}
