package springadvanced.pdp.uz.springadvancedlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Company;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.AddressRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private AddressRepository addressRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }


    public Page<Company> getAllCompanies(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return companyRepository.findAll(pageable);
    }

    public Company getCompanyById(Long id){
        Optional<Company> byId = companyRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto){
        if(companyRepository.existsByStir(companyDto.getStir())){
            return new ApiResponse("company is exist", false);
        }

        Optional<Address> addressById = addressRepository.findById(companyDto.getAddressId());
        if (!addressById.isPresent()){
            return new ApiResponse("address is not exist", false);
        }
        Address address = addressById.get();

        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(address);
        company.setStir(companyDto.getStir());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("company is added", true);
    }

    public ApiResponse editCompany(Long id, CompanyDto companyDto){
        if(companyRepository.existsByStirAndIdNot(companyDto.getStir(), id)){
            return new ApiResponse("company is exist", false);
        }

        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }

        Optional<Address> addressById = addressRepository.findById(companyDto.getAddressId());
        if (!addressById.isPresent()){
            return new ApiResponse("address is not exist", false);
        }
        Address address = addressById.get();

        Company company = byId.get();
        company.setDirectorName(companyDto.getDirectorName());
        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(address);
        companyRepository.save(company);
        return new ApiResponse("address is edited", true);

    }

    public ApiResponse deleteCompanyById(Long id){
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }
        companyRepository.deleteById(id);
        return new ApiResponse("address is deleted", true);

    }


}
