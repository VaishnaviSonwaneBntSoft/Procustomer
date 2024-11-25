package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;

import java.util.List;

public interface EmailAddressService {

    EmailAddressResponse createEmailAddress(EmailAddressRequest emailAddress);

    List<EmailAddressResponse> getAllCustomerEmailAddress();

    EmailAddressResponse getEmailAddress(Long customerNumber);

    void updateEmailAddress(EmailAddressRequest emailAddress, Long customerNumber);

    void deleteEmailAddress(Long customerNumber);
}
