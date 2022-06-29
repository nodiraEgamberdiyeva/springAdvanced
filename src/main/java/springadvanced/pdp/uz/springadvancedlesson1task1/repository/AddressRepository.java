package springadvanced.pdp.uz.springadvancedlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByHomeNumber(String homeNumber);
    boolean existsByHomeNumberAndIdNot(String homeNumber, Long id);
}
