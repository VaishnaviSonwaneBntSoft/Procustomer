package inc.nimbuspay.procustomer.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NationalIdentityTest {

    @Test
    void testNationalIdentityEntityConstruction() {
        UUID nationalId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 123456L;
        NationalIdentity nationalIdentity = new NationalIdentity(nationalId, timestamp, customerNumber, "PASS123", "DL456", "NTI789", false);
        assertNotNull(nationalIdentity.getNationalId());
        assertEquals(customerNumber, nationalIdentity.getCustomerNumber());
        assertEquals("PASS123", nationalIdentity.getPassportNumber());
        assertEquals("DL456", nationalIdentity.getDrivingLicenseNumber());
        assertEquals("NTI789", nationalIdentity.getNationalTaxId());
        assertEquals(timestamp, nationalIdentity.getTimestamp());
        assertFalse(nationalIdentity.isDeleted());
    }

    @Test
    void testGetterSetterMethods() {
        NationalIdentity nationalIdentity = new NationalIdentity();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 654321L;
        nationalIdentity.setCustomerNumber(customerNumber);
        nationalIdentity.setPassportNumber("PASS321");
        nationalIdentity.setDrivingLicenseNumber("DL654");
        nationalIdentity.setNationalTaxId("NTI987");
        nationalIdentity.setTimestamp(timestamp);
        nationalIdentity.setDeleted(true);
        assertEquals(customerNumber, nationalIdentity.getCustomerNumber());
        assertEquals("PASS321", nationalIdentity.getPassportNumber());
        assertEquals("DL654", nationalIdentity.getDrivingLicenseNumber());
        assertEquals("NTI987", nationalIdentity.getNationalTaxId());
        assertEquals(timestamp, nationalIdentity.getTimestamp());
        assertTrue(nationalIdentity.isDeleted());
    }

    @Test
    void testBuilderPattern() {
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 987654L;
        NationalIdentity nationalIdentity = NationalIdentity.builder()
                .customerNumber(customerNumber)
                .passportNumber("PASS987")
                .drivingLicenseNumber("DL987")
                .nationalTaxId("NTI654")
                .timestamp(timestamp)
                .deleted(false)
                .build();
        assertEquals(customerNumber, nationalIdentity.getCustomerNumber());
        assertEquals("PASS987", nationalIdentity.getPassportNumber());
        assertEquals("DL987", nationalIdentity.getDrivingLicenseNumber());
        assertEquals("NTI654", nationalIdentity.getNationalTaxId());
        assertEquals(timestamp, nationalIdentity.getTimestamp());
        assertFalse(nationalIdentity.isDeleted());
    }
}
