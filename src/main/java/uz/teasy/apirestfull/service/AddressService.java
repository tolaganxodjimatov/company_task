package uz.teasy.apirestfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.apirestfull.entity.Address;
import uz.teasy.apirestfull.payload.AddressDTO;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public ApiResponse addAddress(AddressDTO addressDTO) {
        boolean exists = addressRepository.existsByStreetAndAndHomeNumber(addressDTO.getStreet(), addressDTO.getHomeNumber());
        if (exists) return new ApiResponse("Address - already exists!", false);
        Address address = new Address(null, addressDTO.getStreet(), addressDTO.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address - saved OK!", true);


    }

    public List<Address> getAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    public Address getAddressByID(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public ApiResponse editAddressById(Integer id, AddressDTO addressDTO) {
        boolean exists = addressRepository.existsByStreetAndHomeNumberAndAndIdNot(addressDTO.getStreet(), addressDTO.getHomeNumber(),id);
        if (exists) return new ApiResponse("Address - exists!", false);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) return new ApiResponse("Address - exists!", false);

        Address address = optionalAddress.get();
        address.setHomeNumber(addressDTO.getHomeNumber());
        address.setStreet(addressDTO.getStreet());

        addressRepository.save(address);

        return new ApiResponse("Address - edited OK!", true);
    }


    public ApiResponse deleteAddress(Integer id) {
        try {
            addressRepository.deleteById(id);
            return new ApiResponse("Address - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Address - not deleted!", false);

        }
    }
}
