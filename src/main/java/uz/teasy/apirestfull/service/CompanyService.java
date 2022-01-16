package uz.teasy.apirestfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.apirestfull.entity.Address;
import uz.teasy.apirestfull.entity.Company;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.CompanyDTO;
import uz.teasy.apirestfull.repository.AddressRepository;
import uz.teasy.apirestfull.repository.CompanyRepoistory;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepoistory companyRepoistory;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDTO companyDTO) {
        boolean exists = companyRepoistory.existsByCorpName(companyDTO.getCorpName());
        if (exists) return new ApiResponse("Corp - exists", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyDTO.getAddress_id());
        if (!optionalAddress.isPresent()) return new ApiResponse("Address - not found", false);

        Company company = new Company(null, companyDTO.getCorpName(), companyDTO.getDirectorName(), optionalAddress.get());

        companyRepoistory.save(company);
        return new ApiResponse("Company - saved", true);
    }

    public List<Company> getCompany() {
        List<Company> allCompanies = companyRepoistory.findAll();
        return allCompanies;
    }

    public Company getCompanyByID(Integer id) {
        Optional<Company> optionalCompany = companyRepoistory.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            return company;
        }
        return null;
    }

    public ApiResponse editCompanyByID(Integer id, CompanyDTO companyDTO) {
        Optional<Company> optionalCompany = companyRepoistory.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Company - not found", false);
        Company companyToEdit = optionalCompany.get();

        Optional<Address> addressOptional = addressRepository.findById(companyDTO.getAddress_id());
        if (!addressOptional.isPresent()) return new ApiResponse("Address - not found", false);

        companyToEdit.setCorpName(companyDTO.getCorpName());
        companyToEdit.setDirectorName(companyDTO.getDirectorName());
        companyToEdit.setAddress(addressOptional.get());
        companyRepoistory.save(companyToEdit);
        return new ApiResponse("Company - saved", true);

    }

    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepoistory.deleteById(id);
            return new ApiResponse("Company - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Company - not deleted!", false);
        }
    }
}
