package inc.nimbuspay.procustomer.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@Setter
@Getter
@ToString
public class MailAddressException extends RuntimeException {
    private final String exceptionMessage;
    private final Integer statusCode;
    private final HttpStatusCode status;
    private final String workFlow;

    public MailAddressException(String message, Integer statusCode, HttpStatusCode status,
                                String workFlow) {
        super(message);
        this.exceptionMessage = message;
        this.statusCode = statusCode;
        this.status = status;
        this.workFlow = workFlow;
    }
}
