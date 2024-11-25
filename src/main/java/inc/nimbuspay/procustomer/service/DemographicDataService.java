package inc.nimbuspay.procustomer.service;

import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;

import java.util.List;

public interface DemographicDataService {

    DemographicDataResponse createDemographicData(DemographicDataRequest demographicData);

    List<DemographicDataResponse> getAllCustomerDemographicData();

    DemographicDataResponse getDemographicData(Long customerNumber);

    void updateDemographicData(DemographicDataRequest updateDemographicData, Long customerNumber);

    void deleteDemographicData(Long customerNumber);
}
