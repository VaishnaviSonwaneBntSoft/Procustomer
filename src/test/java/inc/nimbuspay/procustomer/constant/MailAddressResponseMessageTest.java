package inc.nimbuspay.procustomer.constant;

import inc.nimbuspay.procustomer.constant.enums.MailAddressResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailAddressResponseMessageTest {

    @Test
    void testMailAddressAlreadyExists() {
        String expectedMessage = "Mail Address with customer number 123456789012 already exists";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_ALREADY_EXISTS.getMessageFormat(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMailAddressNotFound() {
        String expectedMessage = "Mail Address not found for customer number: 987654321012";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_NOT_FOUND.getMessageFormat(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMailAddressNotExists() {
        String expectedMessage = "Failed to retrieve list of mail addresses";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_NOT_EXISTS.getMessageFormat();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFailedToSaveMailAddress() {
        String expectedMessage = "Failed to save Mail Address";
        String actualMessage = MailAddressResponseMessage.FAILED_TO_SAVE_MAIL_ADDRESS.getMessageFormat();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMailAddressSuccessfullyDeleted() {
        String expectedMessage = "Mail Address with customer number 123456789012 deleted successfully";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_SUCCESSFULLY_DELETED.getMessageFormat(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMailAddressSuccessfullyUpdated() {
        String expectedMessage = "Mail Address with customer number 987654321012 updated successfully";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_SUCCESSFULLY_UPDATED.getMessageFormat(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testMailAddressAlreadyDeleted() {
        String expectedMessage = "Mail Address with customer number 123456789012 already deleted";
        String actualMessage = MailAddressResponseMessage.MAIL_ADDRESS_ALREADY_DELETED.getMessageFormat(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }
}

