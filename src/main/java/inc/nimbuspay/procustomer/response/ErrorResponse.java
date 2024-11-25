package inc.nimbuspay.procustomer.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

@ToString
@Setter
@Getter
public class ErrorResponse {
    private int statusCode;
    private String message;
    private HttpStatusCode status;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(int statusCode, String message, HttpStatusCode status) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
    }
}
