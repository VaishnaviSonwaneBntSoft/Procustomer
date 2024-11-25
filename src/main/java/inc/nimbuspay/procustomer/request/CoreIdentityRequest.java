package inc.nimbuspay.procustomer.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CoreIdentityRequest {

    private LocalDateTime timestamp;
    private Long customerNumber;
    private String tenantName;
    private String firstName;
    private String mothersMaidenName;
    private String middleName1;
    private String middleName2;
    private String middleName3;
    private String familySurname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private boolean deleted;
}
