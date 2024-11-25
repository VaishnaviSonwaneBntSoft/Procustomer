package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;

import java.util.List;

public interface PhoneNumberService {

    PhoneNumberResponse createPhoneNumber(PhoneNumberRequest phoneNumberRequest);

    List<PhoneNumberResponse> getAllCustomerPhoneNumber();

    PhoneNumberResponse getPhoneNumber(Long customerNumber);

    void updatePhoneNumber(PhoneNumberRequest phoneNumberRequest, Long customerNumber);

    void deletePhoneNumber(Long customerNumber);
}
