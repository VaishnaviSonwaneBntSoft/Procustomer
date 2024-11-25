package inc.nimbuspay.procustomer.response;

import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.entity.DemographicData;
import inc.nimbuspay.procustomer.entity.EmailAddress;
import inc.nimbuspay.procustomer.entity.MailAddress;
import inc.nimbuspay.procustomer.entity.NationalIdentity;
import inc.nimbuspay.procustomer.entity.PhoneNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDetailsResponse {

    private CoreIdentity coreIdentity;
    private MailAddress mailAddress;
    private EmailAddress emailAddress;
    private DemographicData demographicData;
    private PhoneNumber phoneNumber;
    private NationalIdentity nationalIdentity;

    public CustomerDetailsResponse(CoreIdentity coreIdentity, MailAddress mailAddress, EmailAddress emailAddress,
            DemographicData demographicData, PhoneNumber phoneNumber, NationalIdentity nationalIdentity) {
        this.coreIdentity = coreIdentity;
        this.mailAddress = mailAddress;
        this.emailAddress = emailAddress;
        this.demographicData = demographicData;
        this.phoneNumber = phoneNumber;
        this.nationalIdentity = nationalIdentity;
    }
}
