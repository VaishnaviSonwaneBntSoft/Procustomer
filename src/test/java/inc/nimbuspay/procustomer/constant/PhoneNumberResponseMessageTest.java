package inc.nimbuspay.procustomer.constant;

import inc.nimbuspay.procustomer.constant.enums.PhoneNumberResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneNumberResponseMessageTest {

    @Test
    void testPhoneNumberAlreadyExists() {
        String expectedMessage = "Phone number with customer number 1234567890 already exists";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_ALREADY_EXISTS.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testPhoneNumberNotFound() {
        String expectedMessage = "Phone number not found for customer number: 1234567890";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_NOT_FOUND.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testPhoneNumberNotExists() {
        String expectedMessage = "Phone number not available";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_NOT_EXISTS.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFailedToSavePhoneNumber() {
        String expectedMessage = "Failed to save Phone number for customer number: 1234567890";
        String actualMessage = PhoneNumberResponseMessage.FAILED_TO_SAVE_PHONE_NUMBER.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testPhoneNumberSuccessfullyDeleted() {
        String expectedMessage = "Phone number with customer number 1234567890 deleted successfully";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_DELETED.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testPhoneNumberSuccessfullyUpdated() {
        String expectedMessage = "Phone number with customer number 1234567890 updated successfully";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_UPDATED.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testPhoneNumberAlreadyDeleted() {
        String expectedMessage = "Phone number with customer number 1234567890 already deleted";
        String actualMessage = PhoneNumberResponseMessage.PHONE_NUMBER_ALREADY_DELETED.getMessage(1234567890L);
        assertEquals(expectedMessage, actualMessage);
    }
}
