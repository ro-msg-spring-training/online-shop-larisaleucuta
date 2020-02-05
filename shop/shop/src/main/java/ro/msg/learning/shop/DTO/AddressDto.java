package ro.msg.learning.shop.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AddressDto {
    private Integer addressId;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
}
