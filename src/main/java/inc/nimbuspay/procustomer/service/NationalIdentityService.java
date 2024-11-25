package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;

import java.util.List;

public interface NationalIdentityService {
    NationalIdentityResponse createNationalIdentity(NationalIdentityRequest nationalIdentity);

    List<NationalIdentityResponse> getAllCustomerNationalIdentity();

    NationalIdentityResponse getNationalIdentity(Long customerNumber);

    void updateNationalIdentity(NationalIdentityRequest updateNationalIdentity, Long customerNumber);

    void deleteNationalIdentity(Long customerNumber);
}