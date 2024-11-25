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
public class PhoneNumberResponse {

    private UUID phoneId;
    private LocalDateTime timestamp;
    private Long customerNumber;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String primaryPhoneStatus;
    private boolean isMobile;
    private boolean deleted;
}
