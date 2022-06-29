package springadvanced.pdp.uz.springadvancedlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Worker;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Department;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.ApiResponse;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.CompanyDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.payload.WorkerDto;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.AddressRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.CompanyRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.DepartmentRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.repository.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService {
    private WorkerRepository workerRepository;
    private DepartmentRepository departmentRepository;
    private AddressRepository addressRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }


    public Page<Worker> getAllWorkers(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return workerRepository.findAll(pageable);
    }

    public Worker getWorkerById(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addWorker(WorkerDto workerDto){
        if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber())){
            return new ApiResponse("worker is exist", false);
        }

        Optional<Address> addressById = addressRepository.findById(workerDto.getAddressId());
        if (!addressById.isPresent()){
            return new ApiResponse("address is not exist", false);
        }

        Optional<Department> depById = departmentRepository.findById(workerDto.getDepartmentId());
        if (!depById.isPresent()){
            return new ApiResponse("department is not exist", false);
        }

        Address address = addressById.get();
        Department department = depById.get();

        Worker worker = new Worker();
        worker.setDepartment(department);
        worker.setAddress(address);
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("worker is added", true);
    }

    public ApiResponse editWorker(Long id, WorkerDto workerDto){
        if(workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id)){
            return new ApiResponse("worker is exist", false);
        }

        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }

        Optional<Address> addressById = addressRepository.findById(workerDto.getAddressId());
        if (!addressById.isPresent()){
            return new ApiResponse("worker is not exist", false);
        }

        Optional<Department> depById = departmentRepository.findById(workerDto.getDepartmentId());
        if (!depById.isPresent()){
            return new ApiResponse("department is not exist", false);
        }

        Address address = addressById.get();
        Department department = depById.get();

        Worker worker = byId.get();
        worker.setDepartment(department);
        worker.setAddress(address);
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("worker is edited", true);

    }

    public ApiResponse deleteWorkerById(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("id is not exist", false);
        }
        workerRepository.deleteById(id);
        return new ApiResponse("worker is deleted", true);

    }


}
