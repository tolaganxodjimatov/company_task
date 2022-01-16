package uz.teasy.apirestfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.apirestfull.entity.Address;
import uz.teasy.apirestfull.entity.Department;
import uz.teasy.apirestfull.entity.Worker;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.DepartmentDTO;
import uz.teasy.apirestfull.payload.WorkerDTO;
import uz.teasy.apirestfull.repository.AddressRepository;
import uz.teasy.apirestfull.repository.DepartmentRepository;
import uz.teasy.apirestfull.repository.WorkerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    public ApiResponse addWorker(WorkerDTO workerDTO) {
        boolean exists = workerRepository.existsByPhoneNumber(workerDTO.getPhoneNumber());
        if (exists) return new ApiResponse("Worker - already exists!", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDTO.getAddress_id());
        if (!optionalAddress.isPresent()) return new ApiResponse("Address - does not exists!", false);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartment_id());
        if (!optionalDepartment.isPresent()) return new ApiResponse("Department - does not exists!", false);

        Worker worker = new Worker(null, workerDTO.getName(), workerDTO.getPhoneNumber(), optionalAddress.get(), optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Department - saved OK!", true);
    }

    public List<Worker> getWorkerList() {
        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }

    public Worker getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }


    public ApiResponse editWorkerByID(Integer id, WorkerDTO workerDTO) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Worker - does not exists!", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDTO.getAddress_id());
        if (!optionalAddress.isPresent()) return new ApiResponse("Address - does not exists!", false);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartment_id());
        if (!optionalDepartment.isPresent()) return new ApiResponse("Department - does not exists!", false);

        Worker workerToEdit = optionalWorker.get();
        workerToEdit.setName(workerDTO.getName());
        workerToEdit.setPhoneNumber(workerDTO.getPhoneNumber());
        workerToEdit.setAddress(optionalAddress.get());
        workerToEdit.setDepartment(optionalDepartment.get());

        workerRepository.save(workerToEdit);
        return new ApiResponse("Worker - edited OK!", true);
    }


    public ApiResponse deleteWorkerByID(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Worker - deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Worker - not deleted!", false);
        }

    }


}
