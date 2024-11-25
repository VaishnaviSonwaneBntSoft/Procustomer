package inc.nimbuspay.procustomer.exception;


import inc.nimbuspay.procustomer.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                                                                               WebRequest request) {
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String errorMessage = "Please ensure the request body contains valid JSON data.";
        ErrorResponse errorResponse = new ErrorResponse(statusCode, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CoreIdentityException.class)
    public ResponseEntity<ErrorResponse> handleCoreIdentityException(CoreIdentityException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(CustomerResponseException.class)
    public ResponseEntity<ErrorResponse> handleCustomerResponseException(CustomerResponseException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(DemographicDataException.class)
    public ResponseEntity<ErrorResponse> handleDemographicDataException(DemographicDataException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(EmailAddressException.class)
    public ResponseEntity<ErrorResponse> handleEmailAddressException(EmailAddressException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(MailAddressException.class)
    public ResponseEntity<ErrorResponse> handleMailAddressException(MailAddressException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(NationalIdentityException.class)
    public ResponseEntity<ErrorResponse> handleNationalIdentityException(NationalIdentityException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(PhoneNumberException.class)
    public ResponseEntity<ErrorResponse> handlePhoneNumberException(PhoneNumberException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode(), exception.getExceptionMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }
}
