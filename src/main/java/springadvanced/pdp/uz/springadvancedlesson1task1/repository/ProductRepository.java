package springadvanced.pdp.uz.springadvancedlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Product;

@RepositoryRestResource(path = "product")
public interface ProductRepository extends JpaRepository<Product, Long> {

}
