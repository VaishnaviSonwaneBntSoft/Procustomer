package inc.nimbuspay.procustomer.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerSearchCriteria {
    private Long customerNumber;
    private String firstName;
    private String familySurname;
    private String emailAddressData;
    private String primaryPhoneNumber;
    private String passportNumber;
    private String drivingLicenseNumber;
}
