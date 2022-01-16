package uz.teasy.apirestfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.teasy.apirestfull.entity.Company;
import uz.teasy.apirestfull.entity.Department;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.DepartmentDTO;
import uz.teasy.apirestfull.repository.CompanyRepoistory;
import uz.teasy.apirestfull.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepoistory companyRepoistory;

    public ApiResponse addDepartment(DepartmentDTO departmentDTO) {
        boolean exists = departmentRepository.existsByName(departmentDTO.getName());
        if (exists) return new ApiResponse("Department - already exists!", false);

        Optional<Company> optionalCompany = companyRepoistory.findById(departmentDTO.getCompany_id());
        if (!optionalCompany.isPresent()) return new ApiResponse("Department - doesn't  exists", false);

        Department department = new Department(null, departmentDTO.getName(), optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department - saced!", true);
    }

    public List<Department> getDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return null;
        return optionalDepartment.get();
    }

    public ApiResponse editDepartment(Integer id, DepartmentDTO departmentDTO) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("Department - doesn't  exists", false);

        Optional<Company> optionalCompany = companyRepoistory.findById(departmentDTO.getCompany_id());
        if (!optionalCompany.isPresent()) return new ApiResponse("Company - doesn't  exists", false);

        Department department = optionalDepartment.get();
        department.setName(departmentDTO.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department - edited", true);
    }

    public ApiResponse deleteDepartmentByID(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Department - deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Department - not deleted", false);
        }
    }


}
