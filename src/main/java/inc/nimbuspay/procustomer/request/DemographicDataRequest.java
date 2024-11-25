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
public class DemographicDataRequest {

    private LocalDateTime timestamp;
    private Long customerNumber;
    private String maritalStatus;
    private String occupation;
    private String declaredAnnualIncome;
    private int balanceOpeningDebt;
    private short numberLoans;
    private short numberCreditCards;
    private int totalExistCreditLimit;
    private boolean deleted;
}
