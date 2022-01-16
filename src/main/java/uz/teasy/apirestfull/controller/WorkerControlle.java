package uz.teasy.apirestfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.teasy.apirestfull.entity.Worker;
import uz.teasy.apirestfull.payload.AbsMessage;
import uz.teasy.apirestfull.payload.ApiResponse;
import uz.teasy.apirestfull.payload.WorkerDTO;
import uz.teasy.apirestfull.service.WorkerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerControlle extends AbsMessage {
    @Autowired
    WorkerService workerService;


    @PostMapping
    public ResponseEntity<?> addWorker(@RequestBody WorkerDTO workerDTO) {
        ApiResponse apiResponse = workerService.addWorker(workerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getWorkerList() {
        List<Worker> workerList = workerService.getWorkerList();
        return ResponseEntity.status(201).body(workerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkerById(@PathVariable Integer id) {
        Worker worker = workerService.getWorkerById(id);
        return ResponseEntity.status(200).body(worker);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorkerByID(@PathVariable Integer id, @RequestBody WorkerDTO workerDTO) {
        ApiResponse apiResponse = workerService.editWorkerByID(id, workerDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkerByID(@PathVariable Integer id) {
        ApiResponse apiResponse = workerService.deleteWorkerByID(id);
        return ResponseEntity.status(201).body(apiResponse);

    }


}
