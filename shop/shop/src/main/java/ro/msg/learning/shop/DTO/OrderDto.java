package ro.msg.learning.shop.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OrderDto {

    private LocalDateTime timeStamp;
    private AddressDto deliveryAddress;
    private List<OrderDetailDto> orderDetailDtoList;

}
