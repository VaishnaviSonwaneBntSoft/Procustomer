package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.CoreIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CoreIdentityRepository extends JpaRepository<CoreIdentity, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    CoreIdentity findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.core_identity SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);
}
