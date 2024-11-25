package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchCriteriaTest {

    private CustomerSearchCriteria criteria;

    @BeforeEach
    void setUp() {
        criteria = new CustomerSearchCriteria();
    }

    @Test
    void testCustomerNumberGetterAndSetter() {
        criteria.setCustomerNumber(123L);
        assertEquals(123L, criteria.getCustomerNumber(), "Customer number should be 123");
    }

    @Test
    void testFirstNameGetterAndSetter() {
        criteria.setFirstName("John");
        assertEquals("John", criteria.getFirstName(), "First name should be 'John'");
    }

    @Test
    void testFamilySurnameGetterAndSetter() {
        criteria.setFamilySurname("Doe");
        assertEquals("Doe", criteria.getFamilySurname(), "Family surname should be 'Doe'");
    }

    @Test
    void testEmailAddressDataGetterAndSetter() {
        criteria.setEmailAddressData("john.doe@example.com");
        assertEquals("john.doe@example.com", criteria.getEmailAddressData(), "Email address should be 'john.doe@example.com'");
    }

    @Test
    void testPrimaryPhoneNumberGetterAndSetter() {
        criteria.setPrimaryPhoneNumber("123456789012345678");
        assertEquals("123456789012345678", criteria.getPrimaryPhoneNumber(), "Primary phone number should be '123456789012345678'");
    }

    @Test
    void testPassportNumberGetterAndSetter() {
        criteria.setPassportNumber("AB1234567890123456");
        assertEquals("AB1234567890123456", criteria.getPassportNumber(), "Passport number should be 'AB1234567890123456'");
    }

    @Test
    void testDrivingLicenseNumberGetterAndSetter() {
        criteria.setDrivingLicenseNumber("DL12345678901234567890");
        assertEquals("DL12345678901234567890", criteria.getDrivingLicenseNumber(), "Driving license number should be 'DL12345678901234567890'");
    }
}
