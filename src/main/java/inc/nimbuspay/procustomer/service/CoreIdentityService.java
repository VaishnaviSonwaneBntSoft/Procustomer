package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;
import java.util.List;

public interface CoreIdentityService {

    CoreIdentityResponse createCoreIdentity(CoreIdentityRequest coreIdentity);

    List<CoreIdentityResponse> getAllCoreIdentity();

    CoreIdentityResponse getCoreIdentity(Long accountNumber);

    void updateCoreIdentity(CoreIdentityRequest coreIdentity, Long customerNumber);

    void deleteCoreIdentity(Long customerNumber);

}
