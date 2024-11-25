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
@Table(name = "phone_number", schema = "procustomer")
public class PhoneNumber {

    @Id
    @GeneratedValue
    @Column(name = "phone_id ")
    private UUID phoneId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "timestmp", insertable = false)
    private LocalDateTime timestamp;

    @Column(name = "customer_number", nullable = false)
    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be positive value.")
    private Long customerNumber;

    @Column(name = "primary_phone_number", length = 18)
    @Size(max = 18, message = "Primary phone number must be to 18 character long.")
    private String primaryPhoneNumber;

    @Column(name = "secondary_phone_number", length = 18)
    private String secondaryPhoneNumber;

    @Column(name = "primary_phone_status", length = 10)
    private String primaryPhoneStatus;

    @Column(name = "is_mobile")
    @Builder.Default
    private boolean isMobile = Boolean.FALSE;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}
