package ro.msg.learning.shop.Mapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.AddressDto;
import ro.msg.learning.shop.Entities.Address;

@Component
public class AddressMapper {

    public AddressDto mapAddressToAddressDto(Address address){
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .county(address.getCounty())
                .streetAddress(address.getStreetAddress())
                .build();
    }
    public Address mapAddressDtoToAddress(AddressDto addressDto){
        return Address.builder()
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .county(addressDto.getCounty())
                .streetAddress(addressDto.getStreetAddress())
                .build();
    }

}
