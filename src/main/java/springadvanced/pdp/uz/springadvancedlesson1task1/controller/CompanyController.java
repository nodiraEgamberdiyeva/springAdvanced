package springadvanced.pdp.uz.springadvancedlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Company;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.AddressDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.AddressService;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController( CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public HttpEntity<?> getAllCompanies(@RequestParam int page){
        return ResponseEntity.status(200).body(companyService.getAllCompanies(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCompanyById(@PathVariable Long id){
        Company companyById =companyService.getCompanyById(id);
        if (companyById==null){
            return ResponseEntity.status(409).body(new ApiResponse("Company id is not exist", false));
        }
        return ResponseEntity.status(200).body(companyById);
    }

    @PostMapping
    public HttpEntity<?> addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCompanyById(@PathVariable Long id){
        ApiResponse apiResponse = companyService.deleteCompanyById(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
