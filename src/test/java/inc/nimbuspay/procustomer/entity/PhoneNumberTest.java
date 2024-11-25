package inc.nimbuspay.procustomer.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void testNoArgsConstructor() {
        PhoneNumber phoneNumber = new PhoneNumber();

        assertNotNull(phoneNumber);
        assertNull(phoneNumber.getPhoneId());
        assertNull(phoneNumber.getTimestamp());
        assertNull(phoneNumber.getCustomerNumber());
        assertNull(phoneNumber.getPrimaryPhoneNumber());
        assertNull(phoneNumber.getSecondaryPhoneNumber());
        assertNull(phoneNumber.getPrimaryPhoneStatus());
        assertFalse(phoneNumber.isMobile());
    }

    @Test
    void testAllArgsConstructor() {
        UUID phoneId = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();
        PhoneNumber phoneNumber = new PhoneNumber(phoneId, localDateTime, 1234567890L, "8888146895", "9098877654", "ACTIVE", false, false);

        assertNotNull(phoneNumber);
        assertEquals(phoneId, phoneNumber.getPhoneId());
        assertEquals(localDateTime, phoneNumber.getTimestamp());
        assertEquals(1234567890L, phoneNumber.getCustomerNumber());
        assertEquals("8888146895", phoneNumber.getPrimaryPhoneNumber());
        assertEquals("9098877654", phoneNumber.getSecondaryPhoneNumber());
        assertEquals("ACTIVE", phoneNumber.getPrimaryPhoneStatus());
        assertFalse(phoneNumber.isMobile());
    }

    @Test
    void testSettersAndGetters() {
        PhoneNumber phoneNumber = new PhoneNumber();
        UUID phoneId = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();

        phoneNumber.setPhoneId(phoneId);
        phoneNumber.setTimestamp(localDateTime);
        phoneNumber.setCustomerNumber(1234567890L);
        phoneNumber.setPrimaryPhoneNumber("8888146895");
        phoneNumber.setSecondaryPhoneNumber("9098877654");
        phoneNumber.setPrimaryPhoneStatus("ACTIVE");
        phoneNumber.setMobile(false);

        assertNotNull(phoneNumber);
        assertEquals(phoneId, phoneNumber.getPhoneId());
        assertEquals(localDateTime, phoneNumber.getTimestamp());
        assertEquals(1234567890L, phoneNumber.getCustomerNumber());
        assertEquals("8888146895", phoneNumber.getPrimaryPhoneNumber());
        assertEquals("9098877654", phoneNumber.getSecondaryPhoneNumber());
        assertEquals("ACTIVE", phoneNumber.getPrimaryPhoneStatus());
        assertFalse(phoneNumber.isMobile());
    }

    @Test
    void testBuilder() {
        UUID phoneId = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(phoneId)
                .timestamp(localDateTime)
                .customerNumber(1234567890L)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .build();

        assertNotNull(phoneNumber);
        assertEquals(phoneId, phoneNumber.getPhoneId());
        assertEquals(localDateTime, phoneNumber.getTimestamp());
        assertEquals(1234567890L, phoneNumber.getCustomerNumber());
        assertEquals("8888146895", phoneNumber.getPrimaryPhoneNumber());
        assertEquals("9098877654", phoneNumber.getSecondaryPhoneNumber());
        assertEquals("ACTIVE", phoneNumber.getPrimaryPhoneStatus());
        assertFalse(phoneNumber.isMobile());
    }

    @Test
    void testPhoneIdSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        UUID phoneId = UUID.randomUUID();
        phoneNumber.setPhoneId(phoneId);
        assertEquals(phoneId, phoneNumber.getPhoneId());
    }

    @Test
    void testTimestampSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        LocalDateTime now = LocalDateTime.now();
        phoneNumber.setTimestamp(now);
        assertEquals(now, phoneNumber.getTimestamp());
    }

    @Test
    void testCustomerNumberSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCustomerNumber(1234567890L);
        assertEquals(1234567890L, phoneNumber.getCustomerNumber());
    }

    @Test
    void testPrimaryPhoneNumberSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPrimaryPhoneNumber("8888146895");
        assertEquals("8888146895", phoneNumber.getPrimaryPhoneNumber());
    }

    @Test
    void testSecondaryPhoneNumberSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setSecondaryPhoneNumber("9098877654");
        assertEquals("9098877654", phoneNumber.getSecondaryPhoneNumber());
    }

    @Test
    void testPrimaryPhoneStatusSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPrimaryPhoneStatus("ACTIVE");
        assertEquals("ACTIVE", phoneNumber.getPrimaryPhoneStatus());
    }

    @Test
    void testMobileSetterAndGetter() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setMobile(false);
        assertFalse(phoneNumber.isMobile());
    }
}
