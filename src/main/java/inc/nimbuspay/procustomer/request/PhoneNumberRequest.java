package inc.nimbuspay.procustomer.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PhoneNumberRequest {

    private Long customerNumber;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String primaryPhoneStatus;
    private boolean deleted;
}
