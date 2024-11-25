package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.NationalIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NationalIdentityRepository extends JpaRepository<NationalIdentity, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    NationalIdentity findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.national_identity SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);
}
