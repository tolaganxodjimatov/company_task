package uz.teasy.apirestfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.apirestfull.entity.Address;
import uz.teasy.apirestfull.payload.AbsMessage;
import uz.teasy.apirestfull.payload.AddressDTO;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController extends AbsMessage {
    //Address
    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        ApiResponse apiResponse = addressService.addAddress(addressDTO);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAddress() {
        List<Address> addressList = addressService.getAddress();
        return ResponseEntity.ok(addressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressByID(@PathVariable Integer id) {
        Address addressByID = addressService.getAddressByID(id);
        return ResponseEntity.ok(addressByID);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
        ApiResponse apiResponse = addressService.editAddressById(id, addressDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}
