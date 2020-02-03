package ro.msg.learning.shop.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.Entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByStreetAddressAndCityAndCountryAndCounty(String streetAddress, String city, String country, String county);
}
