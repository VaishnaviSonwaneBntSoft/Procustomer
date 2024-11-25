package inc.nimbuspay.procustomer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class PhoneNumberException extends RuntimeException {

    private final String exceptionMessage;
    private final int statusCode;
    private final HttpStatusCode status;
    private final String workFlow;

    public PhoneNumberException(String exceptionMessage, int statusCode, HttpStatusCode status, String workFlow) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
        this.statusCode = statusCode;
        this.status = status;
        this.workFlow = workFlow;
    }
}
