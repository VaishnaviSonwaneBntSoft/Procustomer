package inc.nimbuspay.procustomer.repository;

import inc.nimbuspay.procustomer.entity.DemographicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DemographicDataRepository extends JpaRepository<DemographicData, UUID> {

    boolean existsByCustomerNumber(Long customerNumber);

    DemographicData findByCustomerNumber(Long customerNumber);

    @Modifying
    @Query(value = "UPDATE procustomer.demographic_data SET deleted = true WHERE customer_number = :customerNumber", nativeQuery = true)
    void softDeleteByCustomerNumber(Long customerNumber);

}
