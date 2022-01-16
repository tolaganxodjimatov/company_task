package uz.teasy.apirestfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.apirestfull.entity.Company;
import uz.teasy.apirestfull.payload.AbsMessage;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.CompanyDTO;
import uz.teasy.apirestfull.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController extends AbsMessage {
    //Company
    //company

    @Autowired
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDTO companyDTO) {
        ApiResponse apiResponse = companyService.addCompany(companyDTO);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getCompany() {
        List<Company> companyList = companyService.getCompany();
        return ResponseEntity.status(HttpStatus.OK).body(companyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyByID(@PathVariable Integer id) {
        Company company = companyService.getCompanyByID(id);
            return ResponseEntity.status(201).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO){
        ApiResponse apiResponse = companyService.editCompanyByID(id, companyDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:401).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompanyByID(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }



}
