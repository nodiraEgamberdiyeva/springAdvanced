package springadvanced.pdp.uz.springadvancedlesson1task1.repository;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import springadvanced.pdp.uz.springadvancedlesson1task1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
