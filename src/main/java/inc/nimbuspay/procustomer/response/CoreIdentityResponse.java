package inc.nimbuspay.procustomer.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;



@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class CoreIdentityResponse {
    private UUID consumerId;
    private LocalDateTime timestamp;
    private Long customerNumber;
    private String tenantName;
    private String firstName;
    private String middleName1;
    private String middleName2;
    private String middleName3;
    private String familySurname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String mothersMaidenName;
    private boolean deleted;
}
