package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.DemographicData;
import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DemographicDataMapper {
    DemographicDataMapper INSTANCE = Mappers.getMapper(DemographicDataMapper.class);

    DemographicData demographicDataRequestToDemographicData(DemographicDataRequest request);

    List<DemographicDataResponse> demographicDataListToDemographicDataResponseList(List<DemographicData> demographicDataList);

    DemographicDataResponse demographicDataToDemographicDataResponse(DemographicData data);
}
