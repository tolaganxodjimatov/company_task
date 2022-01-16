package uz.teasy.apirestfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.apirestfull.entity.Department;
import uz.teasy.apirestfull.payload.AbsMessage;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.DepartmentDTO;
import uz.teasy.apirestfull.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController extends AbsMessage {
    //Department
    //department
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        ApiResponse apiResponse = departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getDepartment() {
        List<Department> departmentList = departmentService.getDepartment();
        return ResponseEntity.status(201).body(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Integer id, @RequestBody DepartmentDTO departmentDTO) {
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentByID(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.deleteDepartmentByID(id);
        return ResponseEntity.status(201).body(apiResponse);

    }


}
