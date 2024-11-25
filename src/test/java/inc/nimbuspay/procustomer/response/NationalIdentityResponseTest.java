package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NationalIdentityResponseTest {

    @Test
    void testNoArgsConstructor() {
        NationalIdentityResponse response = new NationalIdentityResponse();
        assertNotNull(response);
    }

    @Test
    void testAllArgsConstructor() {
        UUID nationalId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 1234567890L;
        String passportNumber = "X1234567";
        String drivingLicenseNumber = "DL123456";
        String nationalTaxId = "TAX123456";
        boolean deleted = false;
        NationalIdentityResponse response = new NationalIdentityResponse(
                nationalId, timestamp, customerNumber, passportNumber, drivingLicenseNumber, nationalTaxId, deleted);
        assertEquals(nationalId, response.getNationalId());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(passportNumber, response.getPassportNumber());
        assertEquals(drivingLicenseNumber, response.getDrivingLicenseNumber());
        assertEquals(nationalTaxId, response.getNationalTaxId());
        assertEquals(deleted, response.isDeleted());
    }

    @Test
    void testBuilder() {
        UUID nationalId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 1234567890L;
        String passportNumber = "X1234567";
        String drivingLicenseNumber = "DL123456";
        String nationalTaxId = "TAX123456";
        boolean deleted = false;
        NationalIdentityResponse response = NationalIdentityResponse.builder()
                .nationalId(nationalId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .passportNumber(passportNumber)
                .drivingLicenseNumber(drivingLicenseNumber)
                .nationalTaxId(nationalTaxId)
                .deleted(deleted)
                .build();
        assertEquals(nationalId, response.getNationalId());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(passportNumber, response.getPassportNumber());
        assertEquals(drivingLicenseNumber, response.getDrivingLicenseNumber());
        assertEquals(nationalTaxId, response.getNationalTaxId());
        assertEquals(deleted, response.isDeleted());
    }

    @Test
    void testToString() {
        UUID nationalId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 1234567890L;
        String passportNumber = "X1234567";
        String drivingLicenseNumber = "DL123456";
        String nationalTaxId = "TAX123456";
        boolean deleted = false;
        NationalIdentityResponse response = NationalIdentityResponse.builder()
                .nationalId(nationalId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .passportNumber(passportNumber)
                .drivingLicenseNumber(drivingLicenseNumber)
                .nationalTaxId(nationalTaxId)
                .deleted(deleted)
                .build();
        String expectedToString = "NationalIdentityResponse(nationalId=" + nationalId +
                ", timestamp=" + timestamp +
                ", customerNumber=" + customerNumber +
                ", passportNumber=" + passportNumber +
                ", drivingLicenseNumber=" + drivingLicenseNumber +
                ", nationalTaxId=" + nationalTaxId +
                ", deleted=" + deleted + ")";
        assertEquals(expectedToString, response.toString());
    }
}
