package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.MailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailAddressRepository extends JpaRepository<MailAddress, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    MailAddress findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.mail_address SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);
}
