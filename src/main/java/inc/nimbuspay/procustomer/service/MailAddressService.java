package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;

import java.util.List;

public interface MailAddressService {
    MailAddressResponse createMailAddress(MailAddressRequest mailAddressRequest);

    List<MailAddressResponse> getAllCustomerMailAddress();

    MailAddressResponse getMailAddress(Long customerNumber);

    void updateMailAddress(MailAddressRequest mailAddressRequest, Long customerNumber);

    void deleteMailAddress(Long customerNumber);
}
