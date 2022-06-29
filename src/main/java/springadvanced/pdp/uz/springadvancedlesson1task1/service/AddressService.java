package springadvanced.pdp.uz.springadvancedlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.AddressDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.AddressRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.CompanyRepository;

import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Page<Address> getAllAddresses(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return addressRepository.findAll(pageable);
    }

    public Address getAddressById(Long id){
        Optional<Address> byId = addressRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addAddress(AddressDto addressDto){
        if(addressRepository.existsByHomeNumber(addressDto.getHomeNumber())){
            return new ApiResponse("address is exist", false);
        }

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("address is added", true);
    }

    public ApiResponse editAddress(Long id, AddressDto addressDto){
        if(addressRepository.existsByHomeNumberAndIdNot(addressDto.getHomeNumber(), id)){
            return new ApiResponse("address is exist", false);
        }

        Optional<Address> addressById = addressRepository.findById(id);
        if (!addressById.isPresent()) {
            return new ApiResponse("address is not exist", false);
        }

        Address address = addressById.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("address is edited", true);

    }

    public ApiResponse deleteCompanyById(Long id){
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }
        addressRepository.deleteById(id);
        return new ApiResponse("address is deleted", true);

    }


}
