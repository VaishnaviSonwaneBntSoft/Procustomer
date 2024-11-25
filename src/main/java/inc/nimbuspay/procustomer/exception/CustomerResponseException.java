package inc.nimbuspay.procustomer.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class CustomerResponseException extends RuntimeException{

    private final String exceptionMessage;
    private final Integer statusCode;
    private final HttpStatusCode status;
    private final String workFlow;

    public CustomerResponseException(String exceptionMessage, Integer statusCode,
                                     HttpStatusCode status, String workFlow) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
        this.statusCode = statusCode;
        this.status = status;
        this.workFlow = workFlow;
    }
}
