package springadvanced.pdp.uz.springadvancedlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Company;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Department;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.DepartmentDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.DepartmentRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.AddressService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.CompanyService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;

    }

    @GetMapping
    public HttpEntity<?> getAllDepartments(@RequestParam int page){
        return ResponseEntity.status(200).body(departmentService.getAllDepartments(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getDepartmentById(@PathVariable Long id){
        Department departmentById =departmentService.getDepartmentById(id);
        if (departmentById==null){
            return ResponseEntity.status(409).body(new ApiResponse("Worker id is not exist", false));
        }
        return ResponseEntity.status(200).body(departmentById);
    }

    @PostMapping
    public HttpEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteDepartmentById(@PathVariable Long id){
        ApiResponse apiResponse = departmentService.deleteDepartmentById(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
