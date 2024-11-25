package inc.nimbuspay.procustomer.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

class CoreIdentityTest {

    @InjectMocks
    private CoreIdentity coreIdentity;

    @Test
    void testConstructorAndGetters() {
        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456789L;
        String tenantName = "TestTenant";
        String firstName = "John";
        String middleName1 = "Michael";
        String middleName2 = "Doe";
        String middleName3 = "William";
        String familySurname = "Smith";
        LocalDate dateOfBirth = LocalDate.of(1990, 5, 15);
        String placeOfBirth = "New York";
        String mothersMaidenName = "Johnson";
        coreIdentity = new CoreIdentity(consumerId, timestamp, customerNumber, tenantName, firstName, familySurname, middleName1,
                middleName2, middleName3, dateOfBirth, placeOfBirth, mothersMaidenName, false);
        Assertions.assertEquals(consumerId, coreIdentity.getConsumerId());
        Assertions.assertEquals(timestamp, coreIdentity.getTimestamp());
        Assertions.assertEquals(customerNumber, coreIdentity.getCustomerNumber());
        Assertions.assertEquals(tenantName, coreIdentity.getTenantName());
        Assertions.assertEquals(firstName, coreIdentity.getFirstName());
        Assertions.assertEquals(middleName1, coreIdentity.getMiddleName1());
        Assertions.assertEquals(middleName2, coreIdentity.getMiddleName2());
        Assertions.assertEquals(middleName3, coreIdentity.getMiddleName3());
        Assertions.assertEquals(familySurname, coreIdentity.getFamilySurname());
        Assertions.assertEquals(dateOfBirth, coreIdentity.getDateOfBirth());
        Assertions.assertEquals(placeOfBirth, coreIdentity.getPlaceOfBirth());
        Assertions.assertEquals(mothersMaidenName, coreIdentity.getMothersMaidenName());
        Assertions.assertFalse(coreIdentity.isDeleted());
    }

    @Test
    void testNoArgsConstructor() {
        coreIdentity = new CoreIdentity();
        Assertions.assertNull(coreIdentity.getConsumerId());
        Assertions.assertNull(coreIdentity.getTimestamp());
        Assertions.assertNull(coreIdentity.getCustomerNumber());
        Assertions.assertNull(coreIdentity.getTenantName());
        Assertions.assertNull(coreIdentity.getFirstName());
        Assertions.assertNull(coreIdentity.getMiddleName1());
        Assertions.assertNull(coreIdentity.getMiddleName2());
        Assertions.assertNull(coreIdentity.getMiddleName3());
        Assertions.assertNull(coreIdentity.getFamilySurname());
        Assertions.assertNull(coreIdentity.getDateOfBirth());
        Assertions.assertNull(coreIdentity.getPlaceOfBirth());
        Assertions.assertNull(coreIdentity.getMothersMaidenName());
        Assertions.assertFalse(coreIdentity.isDeleted());
    }

    @Test
    void testBuilder() {
        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456789L;
        String tenantName = "TestTenant";
        String firstName = "John";
        coreIdentity = CoreIdentity.builder()
                .consumerId(consumerId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .tenantName(tenantName)
                .firstName(firstName)
                .build();
        Assertions.assertEquals(consumerId, coreIdentity.getConsumerId());
        Assertions.assertEquals(timestamp, coreIdentity.getTimestamp());
        Assertions.assertEquals(customerNumber, coreIdentity.getCustomerNumber());
        Assertions.assertEquals(tenantName, coreIdentity.getTenantName());
        Assertions.assertEquals(firstName, coreIdentity.getFirstName());
        Assertions.assertNull(coreIdentity.getMiddleName1());
        Assertions.assertNull(coreIdentity.getMiddleName2());
        Assertions.assertNull(coreIdentity.getMiddleName3());
        Assertions.assertNull(coreIdentity.getFamilySurname());
        Assertions.assertNull(coreIdentity.getDateOfBirth());
        Assertions.assertNull(coreIdentity.getPlaceOfBirth());
        Assertions.assertNull(coreIdentity.getMothersMaidenName());
        Assertions.assertFalse(coreIdentity.isDeleted());
    }

    @Test
    void testToString() {
        coreIdentity = CoreIdentity.builder()
                .consumerId(UUID.randomUUID())
                .customerNumber(123456789L)
                .tenantName("TestTenant")
                .firstName("John")
                .build();
        String toStringResult = coreIdentity.toString();
        Assertions.assertTrue(toStringResult.contains("consumerId=" + coreIdentity.getConsumerId()));
        Assertions.assertTrue(toStringResult.contains("customerNumber=" + coreIdentity.getCustomerNumber()));
        Assertions.assertTrue(toStringResult.contains("tenantName=" + coreIdentity.getTenantName()));
        Assertions.assertTrue(toStringResult.contains("firstName=" + coreIdentity.getFirstName()));
    }
}

