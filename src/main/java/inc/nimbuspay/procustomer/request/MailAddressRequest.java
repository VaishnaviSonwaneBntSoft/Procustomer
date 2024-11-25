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
public class MailAddressRequest {

    private LocalDateTime timestamp;
    private String addressType;
    private Long customerNumber;
    private String buildingNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String country;
    private String stateCounty;
    private String placeLocation;
    private boolean deleted;
}

