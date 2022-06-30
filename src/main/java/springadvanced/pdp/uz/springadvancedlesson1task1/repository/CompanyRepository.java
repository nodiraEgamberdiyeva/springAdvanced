package springadvanced.pdp.uz.springadvancedlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Company;

@RepositoryRestResource(path = "company")
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByStir(String stir);
    boolean existsByStirAndIdNot(String stir, Long id);
}
