package inc.nimbuspay.procustomer.exception;

import inc.nimbuspay.procustomer.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionsTest {

    @InjectMocks
    private GlobalExceptions globalExceptions;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex = Mockito.mock(HttpMessageNotReadableException.class);
        Mockito.when(ex.getMessage()).thenReturn("Test exception");

        ResponseEntity<ErrorResponse> response = globalExceptions.handleHttpMessageNotReadableException(ex, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please ensure the request body contains valid JSON data.", response.getBody().getMessage());
        assertEquals(400, response.getBody().getStatusCode());
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError1 = new FieldError("objectName", "field1", "Error 1");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Error 2");
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError1, fieldError2));

        ResponseEntity<ErrorResponse> response = globalExceptions.handleMethodArgumentNotValidException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains("field1: Error 1"));
        assertTrue(response.getBody().getMessage().contains("field2: Error 2"));
        assertEquals(400, response.getBody().getStatusCode());
    }

    @Test
    void testHandleException() {
        Exception ex = new Exception("General error");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("General error", response.getBody().getMessage());
        assertEquals(500, response.getBody().getStatusCode());
    }

    @Test
    void testHandleCoreIdentityException() {
        CoreIdentityException ex = new CoreIdentityException("Identity error", HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND, "testHandleCoreIdentityException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleCoreIdentityException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Identity error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    @Test
    void testHandleDemographicDataException() {
        DemographicDataException ex = new DemographicDataException("Demographic data error",
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "testHandleDemographicDataException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleDemographicDataException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Demographic data error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    @Test
    void testHandleEmailAddressException() {
        EmailAddressException ex = new EmailAddressException("Email address error",
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "testHandleEmailAddressException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleEmailAddressException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Email address error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    @Test
    void testHandleMailAddressException() {
        MailAddressException ex = new MailAddressException("Mail address error",
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "testHandleMailAddressException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleMailAddressException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Mail address error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    @Test
    void testHandleNationalIdentityException() {
        NationalIdentityException ex = new NationalIdentityException("National identity error",
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "testHandleNationalIdentityException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handleNationalIdentityException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("National identity error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    @Test
    void testHandlePhoneNumberException() {
        PhoneNumberException ex = new PhoneNumberException("Phone number error",
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "testHandlePhoneNumberException");
        ResponseEntity<ErrorResponse> response = globalExceptions.handlePhoneNumberException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Phone number error", response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }
}
