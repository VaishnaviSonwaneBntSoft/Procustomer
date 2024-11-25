package inc.nimbuspay.procustomer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "email_address", schema = "procustomer")
public class EmailAddress {

    @Id
    @GeneratedValue
    private UUID emailId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, name = "timestmp", insertable = false)
    private LocalDateTime timestamp;

    @Column(name = "customer_number", nullable = false)
    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be positive value.")
    private Long customerNumber;

    @Column(name = "email_address")
    @Email(message = "Email address should be valid.")
    private String emailAddressData;

    @Column(name = "email_status")
    private String emailStatus;

    @Column(name = "is_active")
    @Builder.Default
    private boolean isActive = Boolean.TRUE;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}
