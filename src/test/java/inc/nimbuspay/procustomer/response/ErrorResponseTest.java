package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testConstructorWithStatusCodeAndMessage() {
        int expectedStatusCode = 404;
        String expectedMessage = "Resource not found";
        ErrorResponse errorResponse = new ErrorResponse(expectedStatusCode, expectedMessage);
        assertEquals(expectedStatusCode, errorResponse.getStatusCode(), "Status code should match");
        assertEquals(expectedMessage, errorResponse.getMessage(), "Message should match");
        assertNull(errorResponse.getStatus(), "Status should be null for this constructor");
    }

    @Test
    void testConstructorWithAllFields() {
        int expectedStatusCode = 500;
        String expectedMessage = "Internal server error";
        HttpStatus expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(expectedStatusCode, expectedMessage, expectedStatus);
        assertEquals(expectedStatusCode, errorResponse.getStatusCode(), "Status code should match");
        assertEquals(expectedMessage, errorResponse.getMessage(), "Message should match");
        assertEquals(expectedStatus, errorResponse.getStatus(), "Status should match");
    }

    @Test
    void testGettersAndSetters() {
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad request");
        errorResponse.setStatusCode(401);
        errorResponse.setMessage("Unauthorized");
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        assertEquals(401, errorResponse.getStatusCode(), "Status code should be updated");
        assertEquals("Unauthorized", errorResponse.getMessage(), "Message should be updated");
        assertEquals(HttpStatus.UNAUTHORIZED, errorResponse.getStatus(), "Status should be updated");
    }

    @Test
    void testToString() {
        ErrorResponse errorResponse = new ErrorResponse(403, "Forbidden");
        String toStringResult = errorResponse.toString();
        assertTrue(toStringResult.contains("statusCode=403"), "toString should contain statusCode");
        assertTrue(toStringResult.contains("message=Forbidden"), "toString should contain message");
        assertTrue(toStringResult.contains("status="), "toString should not contain status when it's null");
    }
}

