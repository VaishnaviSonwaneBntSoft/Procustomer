package inc.nimbuspay.procustomer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "core_identity", schema = "procustomer")
public class CoreIdentity {

    @Id
    @GeneratedValue
    private UUID consumerId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, name = "timestmp", insertable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be positive value.")
    private Long customerNumber;

    @Size(max = 40, message = "Tenant name be less than or equal to 40 character long.")
    private String tenantName;

    @Size(max = 40, message = "First name must be less than or equal to 40 characters long.")
    private String firstName;

    @Size(max = 40, message = "Family surname must be less than or equal to 40 characters long.")
    private String familySurname;

    @Size(max = 40, message = "Middle name 1 must be less than or equal to 40 characters long.")
    private String middleName1;

    @Size(max = 40, message = "Middle name 2 must be less than or equal to 40 characters long.")
    private String middleName2;

    @Size(max = 40, message = "Middle name 3 must be less than or equal to 40 characters long.")
    private String middleName3;

    @Past(message = "Date of birth must be a past date.")
    private LocalDate dateOfBirth;

    @Size(max = 40, message = "Place of birth must be less than or equal to 40 characters long.")
    private String placeOfBirth;

    @Size(max = 40, message = "Mother's maiden name must be less than or equal to 40 characters long.")
    private String mothersMaidenName;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}