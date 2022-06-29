package springadvanced.pdp.uz.springadvancedlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.AddressDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.service.AddressService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public HttpEntity<?> getAllAddresses(@RequestParam int page){
        return ResponseEntity.status(200).body(addressService.getAllAddresses(page));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAddressById(@PathVariable Long id){
        Address addressById = addressService.getAddressById(id);
        if (addressById==null){
            return ResponseEntity.status(409).body(new ApiResponse("Address id is not exist", false));
        }
        return ResponseEntity.status(200).body(addressById);
    }

    @PostMapping
    public HttpEntity<?> addAddress(@Valid  @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAddress(@Valid @PathVariable Long id, @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAddressById(@PathVariable Long id){
        ApiResponse apiResponse = addressService.deleteCompanyById(id);
        return ResponseEntity.status(apiResponse.isStatus()?202:409).body(apiResponse);
    }
}
