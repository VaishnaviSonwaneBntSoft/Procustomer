package inc.nimbuspay.procustomer.constant;

import inc.nimbuspay.procustomer.constant.enums.CoreIdentityResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoreIdentityResponseMessageTest {

    @Test
    void testCoreIdentityAlreadyExists() {
        String expectedMessage = "Core Identity with customer number 123456789012 already exists";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_ALREADY_EXISTS.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCoreIdentityNotFound() {
        String expectedMessage = "Core Identity with customer number 987654321012 is not found";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_NOT_FOUND.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCoreIdentityNotExists() {
        String expectedMessage = "Core Identity not available";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_NOT_EXISTS.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFailedToSaveCoreIdentity() {
        String expectedMessage = "Failed to save Core Identity for customer number: 123456789012";
        String actualMessage = CoreIdentityResponseMessage.FAILED_TO_SAVE_CORE_IDENTITY.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCoreIdentitySuccessfullyUpdated() {
        String expectedMessage = "Core Identity with customer number 987654321012 updated successfully";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_UPDATED.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCoreIdentitySuccessfullyDeleted() {
        String expectedMessage = "Core Identity with customer number 123456789012 is deleted successfully";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCoreIdentityAlreadyDeleted() {
        String expectedMessage = "Core Identity with customer number 123456789012 is already deleted";
        String actualMessage = CoreIdentityResponseMessage.CORE_IDENTITY_ALREADY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }
}

