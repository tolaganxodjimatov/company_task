package uz.teasy.apirestfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.teasy.apirestfull.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByStreetAndAndHomeNumber(String street, String homeNumber);
   boolean existsByStreetAndHomeNumberAndAndIdNot(String street, String homeNumber,Integer id);
  // SELECT * FROM address WHERE street='' and id<>1
}
