package springadvanced.pdp.uz.springadvancedlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Department;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Worker;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.DepartmentDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.WorkerDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.AddressService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.CompanyService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.DepartmentService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private WorkerService workerService;


    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public HttpEntity<?> getAllWorkers(@RequestParam int page){
        return ResponseEntity.status(200).body(workerService.getAllWorkers(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDepartmentById(@PathVariable Long id){
        Worker worker =workerService.getWorkerById(id);
        if (worker==null){
            return ResponseEntity.status(409).body(new ApiResponse("Worker id is not exist", false));
        }
        return ResponseEntity.status(200).body(worker);
    }

    @PostMapping
    public HttpEntity<?> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editWorker(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkerById(@PathVariable Long id){
        ApiResponse apiResponse = workerService.deleteWorkerById(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
