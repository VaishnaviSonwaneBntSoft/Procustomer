package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    Optional<PhoneNumber> findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.phone_number SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);
}
