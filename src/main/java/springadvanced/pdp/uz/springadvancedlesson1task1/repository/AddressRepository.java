package springadvanced.pdp.uz.springadvancedlesson1task1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Address;
import springadvanced.pdp.uz.springadvancedlesson1task1.projection.CustomAddress;

@RepositoryRestResource(path = "address", collectionResourceRel = "list", excerptProjection = CustomAddress.class)
public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByHomeNumber(String homeNumber);
    boolean existsByHomeNumberAndIdNot(String homeNumber, Long id);

    @RestResource(path = "byHomeNumber")
    Page findAllByHomeNumber(@Param("homeNumber") String homeNumber, Pageable pageable);
}
