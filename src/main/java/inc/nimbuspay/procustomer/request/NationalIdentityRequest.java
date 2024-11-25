package inc.nimbuspay.procustomer.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class NationalIdentityRequest {

    private LocalDateTime timestamp;
    private Long customerNumber;
    private String passportNumber;
    private String drivingLicenseNumber;
    private String nationalTaxId;
}
