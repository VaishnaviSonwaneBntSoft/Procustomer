package inc.nimbuspay.procustomer.constant;

import inc.nimbuspay.procustomer.constant.enums.DemographicDataResponseMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DemographicDataResponseMessageTest {

    @Test
    void testDemographicDataAlreadyExists() {
        String expectedMessage = "Demographic data with customer number 123456789012 already exists";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_ALREADY_EXISTS.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDemographicDataNotFound() {
        String expectedMessage = "Demographic data not found for customer number: 987654321012";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDemographicDataNotExists() {
        String expectedMessage = "Demographic data not available";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_EXISTS.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFailedToSaveDemographicData() {
        String expectedMessage = "Failed to save Demographic data for customer number: 123456789012";
        String actualMessage = DemographicDataResponseMessage.FAILED_TO_SAVE_DEMOGRAPHIC_DATA.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDemographicDataSuccessfullyDeleted() {
        String expectedMessage = "Demographic data with customer number 123456789012 deleted successfully";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDemographicDataSuccessfullyUpdated() {
        String expectedMessage = "Demographic data with customer number 987654321012 updated successfully";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_UPDATED.getMessage(987654321012L);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDemographicDataAlreadyDeleted() {
        String expectedMessage = "Demographic data with customer number 123456789012 already deleted";
        String actualMessage = DemographicDataResponseMessage.DEMOGRAPHIC_DATA_ALREADY_DELETED.getMessage(123456789012L);
        assertEquals(expectedMessage, actualMessage);
    }
}
