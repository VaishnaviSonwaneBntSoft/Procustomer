package inc.nimbuspay.procustomer.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NationalIdentityResponse {

    private UUID nationalId;
    private LocalDateTime timestamp;
    private Long customerNumber;
    private String passportNumber;
    private String drivingLicenseNumber;
    private String nationalTaxId;
    private boolean deleted;
}
