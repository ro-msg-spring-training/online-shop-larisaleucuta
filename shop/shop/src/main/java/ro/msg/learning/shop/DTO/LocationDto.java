package ro.msg.learning.shop.DTO;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LocationDto implements Serializable {

    private Integer locationId;
    private String name;
    private AddressDto locationAddressDto;

}
