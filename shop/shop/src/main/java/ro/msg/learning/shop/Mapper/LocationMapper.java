package ro.msg.learning.shop.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.LocationDto;
import ro.msg.learning.shop.Entities.Location;

@Component
@RequiredArgsConstructor
public class LocationMapper {
    private final AddressMapper addressMapper;

    public LocationDto mapLocationToLocationDto(Location location) {
        return LocationDto.builder()
                .name(location.getName())
                .locationAddressDto(addressMapper.mapAddressToAddressDto(location.getLocationAddress()))
                .build();
    }

    public Location mapLocationDtoToLocation(LocationDto locationDto) {
        return Location.builder()
                .name(locationDto.getName())
                .locationAddress(addressMapper.mapAddressDtoToAddress(locationDto.getLocationAddressDto()))
                .build();
    }
}
