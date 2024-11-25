package inc.nimbuspay.procustomer.entity;

import jakarta.persistence.*;
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
@Table(name = "national_identity", schema = "procustomer")
public class NationalIdentity {

    @Id
    @GeneratedValue
    private UUID nationalId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, name = "timestmp", insertable = false)
    private LocalDateTime timestamp;

    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be positive value.")
    private Long customerNumber;

    @Size(max = 18 , message = "Passport number must be up to 18 characters long.")
    private String passportNumber;

    @Size(max=20 ,message = "Driving license number must be up to 20 character long.")
    private String drivingLicenseNumber;

    @Size(max = 20 , message = "National tax id must be up to 20 character long.")
    private String nationalTaxId;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}
