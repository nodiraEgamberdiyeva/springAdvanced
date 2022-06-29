package springadvanced.pdp.uz.springadvancedlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Company;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Department;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.DepartmentDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.AddressRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.CompanyRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {
    private CompanyRepository companyRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
        public DepartmentService(CompanyRepository companyRepository, DepartmentRepository departmentRepository) {
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
    }


    public Page<Department> getAllDepartments(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return departmentRepository.findAll(pageable);
    }

    public Department getDepartmentById(Long id){
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto){
        if(departmentRepository.existsByName(departmentDto.getName())){
            return new ApiResponse("Department is exist", false);
        }

        Optional<Company> companyById = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyById.isPresent()){
            return new ApiResponse("company is not exist", false);
        }
        Company company = companyById.get();

        Department department = new Department();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department is added", true);
    }

    public ApiResponse editDepartment(Long id, DepartmentDto departmentDto){
        if(departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id)){
            return new ApiResponse("Department is exist", false);
        }

        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }

        Optional<Company> companyById = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyById.isPresent()){
            return new ApiResponse("company is not exist", false);
        }
        Company company = companyById.get();

        Department department = byId.get();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department is added", true);

    }

    public ApiResponse deleteDepartmentById(Long id){
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("Department is deleted", true);

    }


}
