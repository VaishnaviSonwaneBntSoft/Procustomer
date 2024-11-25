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
public class DemographicDataResponse {

    private UUID demographicId;
    private LocalDateTime timestamp;
    private Long customerNumber;
    private String maritalStatus;
    private String declaredAnnualIncome;
    private String occupation;
    private int balanceOpeningDebt;
    private short numberLoans;
    private short numberCreditCards;
    private int totalExistCreditLimit;
    private boolean deleted;
}
