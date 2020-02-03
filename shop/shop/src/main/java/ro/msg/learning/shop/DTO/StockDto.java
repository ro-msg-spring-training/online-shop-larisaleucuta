package ro.msg.learning.shop.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StockDto {

    private Integer quantity;
    private Integer productId;
    private LocationDto locationDto;




}
