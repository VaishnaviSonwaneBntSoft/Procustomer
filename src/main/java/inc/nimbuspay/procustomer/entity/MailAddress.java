package inc.nimbuspay.procustomer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "mail_address", schema = "procustomer")
public class MailAddress {

    @Id
    @GeneratedValue
    private UUID addressId;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "timestmp", insertable = false)
    private LocalDateTime timestamp;
    
    @NotNull(message = "Address type cannot be null.")
    @Size(max = 25, message = "Address type must be less than or equal to 25 characters long.")
    private String addressType;

    @NotNull(message = "Customer number cannot be null.")
    @Positive(message = "Customer number must be posititve value.")
    private Long customerNumber;

    @Size(max = 10 , message = "Building number must be less than or equal to 10 characters long.")
    @Pattern(regexp = "\\d+", message = "Building number must be contain only digits.")
    private String buildingNumber;

    @Size(max = 40,message = "Adrress line 1 must be less than or equal to 40 character long.")
    private String addressLine1;

    @Size(max=40, message = "Address line 2 must be less than or equal to 40 character long.")
    private String addressLine2;

    @Size(max=40, message = "Address line 3 must be less than or equal to 40 character long.")
    private String addressLine3;

    @Size(max=40, message = "Place location must be less than or equal to 40 character long.")
    private String placeLocation;

    @Size(max=40, message = "State country must be less than or equal to 40 character long.")
    private String stateCounty;

    @Size(max=40, message = "Country must be less than or equal to 40 character long.")
    private String country;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;

}
