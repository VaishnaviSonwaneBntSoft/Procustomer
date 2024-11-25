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
public class EmailAddressResponse {
    private UUID emailId;
    private LocalDateTime timestamp;
    private Long customerNumber;
    private String emailAddressData;
    private String emailStatus;

    @Builder.Default
    private boolean isActive = Boolean.TRUE;

    private boolean deleted;
}
