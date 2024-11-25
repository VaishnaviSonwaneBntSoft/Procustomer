package inc.nimbuspay.procustomer.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MailAddressResponse {

    private UUID addressId;
    private LocalDateTime timestamp;
    private String addressType;
    private Long customerNumber;
    private String buildingNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String placeLocation;
    private String stateCounty;
    private String country;
    private boolean deleted;
}
