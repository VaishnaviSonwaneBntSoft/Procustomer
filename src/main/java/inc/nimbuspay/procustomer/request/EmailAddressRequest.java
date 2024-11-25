package inc.nimbuspay.procustomer.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class EmailAddressRequest {
    private Long customerNumber;
    private String emailAddressData;
    private String emailStatus;
}
