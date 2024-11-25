package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    EmailAddress findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.email_address SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);
}
