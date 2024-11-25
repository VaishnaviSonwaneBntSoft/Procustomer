package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;

import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.entity.DemographicData;
import inc.nimbuspay.procustomer.entity.EmailAddress;
import inc.nimbuspay.procustomer.entity.MailAddress;
import inc.nimbuspay.procustomer.entity.NationalIdentity;
import inc.nimbuspay.procustomer.entity.PhoneNumber;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataResponseTest {

    @Test
    void testDemographicDataResponse() {
        DemographicData demographicData = new DemographicData();
        demographicData.setMaritalStatus("Single");
        CustomerDetailsResponse customerDataResponse = new CustomerDetailsResponse();
        customerDataResponse.setDemographicData(demographicData);
        assertNotNull(customerDataResponse.getDemographicData(), "DemographicDataResponse should not be null");
        assertEquals("Single", customerDataResponse.getDemographicData().getMaritalStatus(), "Marital status should be 'Single'");
    }

    @Test
    void testEmailAddressResponse() {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setEmailAddressData("test@example.com");
        CustomerDetailsResponse customerDataResponse = new CustomerDetailsResponse();
        customerDataResponse.setEmailAddress(emailAddress);
        assertNotNull(customerDataResponse.getEmailAddress(), "EmailAddressResponse should not be null");
        assertEquals("test@example.com", customerDataResponse.getEmailAddress().getEmailAddressData(), "Email address should be 'test@example.com'");
    }

    @Test
    void testMailAddressResponse() {
        MailAddress mailAddress = new MailAddress();
        mailAddress.setAddressLine1("123 Main St");
        CustomerDetailsResponse customerDataResponse = new CustomerDetailsResponse();
        customerDataResponse.setMailAddress(mailAddress);
        assertNotNull(customerDataResponse.getMailAddress(), "MailAddressResponse should not be null");
        assertEquals("123 Main St", customerDataResponse.getMailAddress().getAddressLine1(), "Address line 1 should be '123 Main St'");
    }

    @Test
    void testNationalIdentityResponse() {
        NationalIdentity nationalIdentity = new NationalIdentity();
        nationalIdentity.setPassportNumber("A12345678");
        CustomerDetailsResponse customerDataResponse = new CustomerDetailsResponse();
        customerDataResponse.setNationalIdentity(nationalIdentity);
        assertNotNull(customerDataResponse.getNationalIdentity(), "NationalIdentityResponse should not be null");
        assertEquals("A12345678", customerDataResponse.getNationalIdentity().getPassportNumber(), "Passport number should be 'A12345678'");
    }

    @Test
    void testPhoneNumberResponse() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPrimaryPhoneNumber("123-456-7890");
        CustomerDetailsResponse customerDataResponse = new CustomerDetailsResponse();
        customerDataResponse.setPhoneNumber(phoneNumber);
        assertNotNull(customerDataResponse.getPhoneNumber(), "PhoneNumberResponse should not be null");
        assertEquals("123-456-7890", customerDataResponse.getPhoneNumber().getPrimaryPhoneNumber(), "Phone number should be '123-456-7890'");
    }

    @Test
    void testNoArgsConstructor() {
        CustomerDetailsResponse response = new CustomerDetailsResponse();
        assertNull(response.getCoreIdentity(), "CoreIdentity should be null");
        assertNull(response.getMailAddress(), "MailAddress should be null");
        assertNull(response.getEmailAddress(), "EmailAddress should be null");
        assertNull(response.getDemographicData(), "DemographicData should be null");
        assertNull(response.getPhoneNumber(), "PhoneNumber should be null");
        assertNull(response.getNationalIdentity(), "NationalIdentity should be null");
    }

    @Test
    void testAllArgsConstructor() {
        CoreIdentity coreIdentity = new CoreIdentity();
        MailAddress mailAddress = new MailAddress();
        EmailAddress emailAddress = new EmailAddress();
        DemographicData demographicData = new DemographicData();
        PhoneNumber phoneNumber = new PhoneNumber();
        NationalIdentity nationalIdentity = new NationalIdentity();

        CustomerDetailsResponse response = new CustomerDetailsResponse(
                coreIdentity, mailAddress, emailAddress, demographicData, phoneNumber, nationalIdentity);

        assertEquals(coreIdentity, response.getCoreIdentity(), "CoreIdentity should match");
        assertEquals(mailAddress, response.getMailAddress(), "MailAddress should match");
        assertEquals(emailAddress, response.getEmailAddress(), "EmailAddress should match");
        assertEquals(demographicData, response.getDemographicData(), "DemographicData should match");
        assertEquals(phoneNumber, response.getPhoneNumber(), "PhoneNumber should match");
        assertEquals(nationalIdentity, response.getNationalIdentity(), "NationalIdentity should match");
    }

    @Test
    void testSettersAndGetters() {
        CustomerDetailsResponse response = new CustomerDetailsResponse();

        CoreIdentity coreIdentity = new CoreIdentity();
        response.setCoreIdentity(coreIdentity);
        assertEquals(coreIdentity, response.getCoreIdentity(), "CoreIdentity should match");

        MailAddress mailAddress = new MailAddress();
        response.setMailAddress(mailAddress);
        assertEquals(mailAddress, response.getMailAddress(), "MailAddress should match");

        EmailAddress emailAddress = new EmailAddress();
        response.setEmailAddress(emailAddress);
        assertEquals(emailAddress, response.getEmailAddress(), "EmailAddress should match");

        DemographicData demographicData = new DemographicData();
        response.setDemographicData(demographicData);
        assertEquals(demographicData, response.getDemographicData(), "DemographicData should match");

        PhoneNumber phoneNumber = new PhoneNumber();
        response.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, response.getPhoneNumber(), "PhoneNumber should match");

        NationalIdentity nationalIdentity = new NationalIdentity();
        response.setNationalIdentity(nationalIdentity);
        assertEquals(nationalIdentity, response.getNationalIdentity(), "NationalIdentity should match");
    }
}

