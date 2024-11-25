package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NationalIdentityRequestTests {

    @Test
    void testDefaultConstructor() {
        NationalIdentityRequest request = new NationalIdentityRequest();
        assertNull(request.getTimestamp());
        assertNull(request.getCustomerNumber());
        assertNull(request.getPassportNumber());
        assertNull(request.getDrivingLicenseNumber());
        assertNull(request.getNationalTaxId());
    }

    @Test
    void testGettersAndSetters() {
        NationalIdentityRequest request = new NationalIdentityRequest();
        LocalDateTime now = LocalDateTime.now();
        Long customerNumber = 123456789L;
        String passportNumber = "P1234567";
        String drivingLicenseNumber = "D1234567";
        String nationalTaxId = "T1234567";
        request.setTimestamp(now);
        request.setCustomerNumber(customerNumber);
        request.setPassportNumber(passportNumber);
        request.setDrivingLicenseNumber(drivingLicenseNumber);
        request.setNationalTaxId(nationalTaxId);
        assertEquals(now, request.getTimestamp());
        assertEquals(customerNumber, request.getCustomerNumber());
        assertEquals(passportNumber, request.getPassportNumber());
        assertEquals(drivingLicenseNumber, request.getDrivingLicenseNumber());
        assertEquals(nationalTaxId, request.getNationalTaxId());
    }

    @Test
    void testToString() {
        LocalDateTime now = LocalDateTime.now();
        Long customerNumber = 123456789L;
        String passportNumber = "P1234567";
        String drivingLicenseNumber = "D1234567";
        String nationalTaxId = "T1234567";
        NationalIdentityRequest request = new NationalIdentityRequest();
        request.setTimestamp(now);
        request.setCustomerNumber(customerNumber);
        request.setPassportNumber(passportNumber);
        request.setDrivingLicenseNumber(drivingLicenseNumber);
        request.setNationalTaxId(nationalTaxId);
        String expectedToString = String.format("NationalIdentityRequest(timestamp=%s, customerNumber=%d, passportNumber=%s, drivingLicenseNumber=%s, nationalTaxId=%s)",
                now, customerNumber, passportNumber, drivingLicenseNumber, nationalTaxId);
        assertEquals(expectedToString, request.toString());
    }
}
