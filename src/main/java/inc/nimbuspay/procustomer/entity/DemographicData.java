package inc.nimbuspay.procustomer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "demographic_data", schema = "procustomer")
public class DemographicData {

    @Id
    @GeneratedValue
    private UUID demographicId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "timestmp", insertable = false)
    private LocalDateTime timestamp;

    @Column(name ="customer_number")
    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be positive value.")
    private Long customerNumber;

    @Column(name ="marital_status")
    @Size(max = 10, message = "Marital status must be less than or equal to 10 characters long.")
    private String maritalStatus;

    @Column(name ="declared_annual_income")
    @Size(max = 10 , message = "Declared annual income must be less than or equal to 10 character long.")
    private String declaredAnnualIncome;

    @Column(name ="occupation")
    @Size(max = 25, message = "Occupation must be less than or equal to 25 character long.")
    private String occupation;

    @Column(name ="bal_opening_debt")
    @Min(value = 0, message = "Balance opening debt must be zero or a positive value.")
    private int balanceOpeningDebt;

    @Column(name ="num_loans")
    @Min(value = 0, message = "Number of loans limit must be zero or a positive value.")
    private short numberLoans;

    @Column(name ="num_credit_cards")
    @Min(value = 0, message = "Total number of credit cards limit must be zero or a positive value.")
    private short numberCreditCards;

    @Column(name ="tot_exist_credit_limit")
    @Min(value = 0, message = "Total existing credit limit must be zero or a positive value.")
    private int totalExistCreditLimit;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}
