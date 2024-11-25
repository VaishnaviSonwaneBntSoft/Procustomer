package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CoreIdentityResponseTest {

    @Test
    void testAllArgsConstructor() {
        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456L;
        String tenantName = "TenantName";
        String firstName = "John";
        String middleName1 = "Paul";
        String middleName2 = "George";
        String middleName3 = "Ringo";
        String familySurname = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String placeOfBirth = "City, Country";
        String mothersMaidenName = "Smith";
        boolean deleted = false;

        CoreIdentityResponse response = new CoreIdentityResponse(
                consumerId, timestamp, customerNumber, tenantName, firstName,
                middleName1, middleName2, middleName3, familySurname, dateOfBirth,
                placeOfBirth, mothersMaidenName, deleted
        );

        assertEquals(consumerId, response.getConsumerId());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(tenantName, response.getTenantName());
        assertEquals(firstName, response.getFirstName());
        assertEquals(middleName1, response.getMiddleName1());
        assertEquals(middleName2, response.getMiddleName2());
        assertEquals(middleName3, response.getMiddleName3());
        assertEquals(familySurname, response.getFamilySurname());
        assertEquals(dateOfBirth, response.getDateOfBirth());
        assertEquals(placeOfBirth, response.getPlaceOfBirth());
        assertEquals(mothersMaidenName, response.getMothersMaidenName());
        assertFalse(response.isDeleted());
    }

    @Test
    void testNoArgsConstructor() {
        CoreIdentityResponse response = new CoreIdentityResponse();

        assertNull(response.getConsumerId());
        assertNull(response.getTimestamp());
        assertNull(response.getCustomerNumber());
        assertNull(response.getTenantName());
        assertNull(response.getFirstName());
        assertNull(response.getMiddleName1());
        assertNull(response.getMiddleName2());
        assertNull(response.getMiddleName3());
        assertNull(response.getFamilySurname());
        assertNull(response.getDateOfBirth());
        assertNull(response.getPlaceOfBirth());
        assertNull(response.getMothersMaidenName());
        assertFalse(response.isDeleted());
    }

    @Test
    void testBuilder() {
        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456L;
        String tenantName = "TenantName";
        String firstName = "John";
        String middleName1 = "Paul";
        String middleName2 = "George";
        String middleName3 = "Ringo";
        String familySurname = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String placeOfBirth = "City, Country";
        String mothersMaidenName = "Smith";
        boolean deleted = false;

        CoreIdentityResponse response = CoreIdentityResponse.builder()
                .consumerId(consumerId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .tenantName(tenantName)
                .firstName(firstName)
                .middleName1(middleName1)
                .middleName2(middleName2)
                .middleName3(middleName3)
                .familySurname(familySurname)
                .dateOfBirth(dateOfBirth)
                .placeOfBirth(placeOfBirth)
                .mothersMaidenName(mothersMaidenName)
                .deleted(deleted)
                .build();

        assertEquals(consumerId, response.getConsumerId());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(tenantName, response.getTenantName());
        assertEquals(firstName, response.getFirstName());
        assertEquals(middleName1, response.getMiddleName1());
        assertEquals(middleName2, response.getMiddleName2());
        assertEquals(middleName3, response.getMiddleName3());
        assertEquals(familySurname, response.getFamilySurname());
        assertEquals(dateOfBirth, response.getDateOfBirth());
        assertEquals(placeOfBirth, response.getPlaceOfBirth());
        assertEquals(mothersMaidenName, response.getMothersMaidenName());
        assertFalse(response.isDeleted());
    }

    @Test
    void testSetters() {
        CoreIdentityResponse response = new CoreIdentityResponse();

        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456L;
        String tenantName = "TenantName";
        String firstName = "John";
        String middleName1 = "Paul";
        String middleName2 = "George";
        String middleName3 = "Ringo";
        String familySurname = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String placeOfBirth = "City, Country";
        String mothersMaidenName = "Smith";
        boolean deleted = false;

        response.setConsumerId(consumerId);
        response.setTimestamp(timestamp);
        response.setCustomerNumber(customerNumber);
        response.setTenantName(tenantName);
        response.setFirstName(firstName);
        response.setMiddleName1(middleName1);
        response.setMiddleName2(middleName2);
        response.setMiddleName3(middleName3);
        response.setFamilySurname(familySurname);
        response.setDateOfBirth(dateOfBirth);
        response.setPlaceOfBirth(placeOfBirth);
        response.setMothersMaidenName(mothersMaidenName);
        response.setDeleted(deleted);

        assertEquals(consumerId, response.getConsumerId());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(tenantName, response.getTenantName());
        assertEquals(firstName, response.getFirstName());
        assertEquals(middleName1, response.getMiddleName1());
        assertEquals(middleName2, response.getMiddleName2());
        assertEquals(middleName3, response.getMiddleName3());
        assertEquals(familySurname, response.getFamilySurname());
        assertEquals(dateOfBirth, response.getDateOfBirth());
        assertEquals(placeOfBirth, response.getPlaceOfBirth());
        assertEquals(mothersMaidenName, response.getMothersMaidenName());
        assertFalse(response.isDeleted());
    }

    @Test
    void testToString() {
        UUID consumerId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456L;
        String tenantName = "TenantName";
        String firstName = "John";
        String middleName1 = "Paul";
        String middleName2 = "George";
        String middleName3 = "Ringo";
        String familySurname = "Doe";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        String placeOfBirth = "City, Country";
        String mothersMaidenName = "Smith";
        boolean deleted = false;

        CoreIdentityResponse response = new CoreIdentityResponse(
                consumerId, timestamp, customerNumber, tenantName, firstName,
                middleName1, middleName2, middleName3, familySurname, dateOfBirth,
                placeOfBirth, mothersMaidenName, deleted
        );

        String expectedToString = "CoreIdentityResponse(consumerId=" + consumerId +
                ", timestamp=" + timestamp +
                ", customerNumber=" + customerNumber +
                ", tenantName=" + tenantName +
                ", firstName=" + firstName +
                ", middleName1=" + middleName1 +
                ", middleName2=" + middleName2 +
                ", middleName3=" + middleName3 +
                ", familySurname=" + familySurname +
                ", dateOfBirth=" + dateOfBirth +
                ", placeOfBirth=" + placeOfBirth +
                ", mothersMaidenName=" + mothersMaidenName +
                ", deleted=" + deleted + ')';

        assertEquals(expectedToString, response.toString());
    }
}

