package inc.nimbuspay.procustomer.constant;

import inc.nimbuspay.procustomer.constant.enums.NationalIdentityResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NationalIdentityResponseMessageTest {

    @Test
    void testNationalIdentityAlreadyExists() {
        String expectedMessage = "National identity with customer number 123456789012 already exists";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_EXISTS.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNationalIdentityNotFound() {
        String expectedMessage = "National identity not found for customer number: 987654321012";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNationalIdentityNotExists() {
        String expectedMessage = "National identity not available";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_EXISTS.getMessageFormat();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFailedToSaveNationalIdentity() {
        String expectedMessage = "Failed to save national identity for customer number: 123456789012";
        String actualMessage = NationalIdentityResponseMessage.FAILED_TO_SAVE_NATIONAL_IDENTITY.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNationalIdentitySuccessfullyDeleted() {
        String expectedMessage = "National identity with customer number 123456789012 deleted successfully";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_SUCCESSFULLY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNationalIdentitySuccessfullyUpdated() {
        String expectedMessage = "National identity with customer number 987654321012 updated successfully";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_SUCCESSFULLY_UPDATED.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNationalIdentityAlreadyDeleted() {
        String expectedMessage = "National identity with customer number 123456789012 already deleted";
        String actualMessage = NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }
}
