package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoreIdentityMapper {
    CoreIdentityMapper INSTANCE = Mappers.getMapper(CoreIdentityMapper.class);

    CoreIdentity coreIdentityRequestToCoreIdentity(CoreIdentityRequest coreIdentityRequest);

    List<CoreIdentityResponse> coreIdentityListToCoreIdentityResponseList(List<CoreIdentity> coreIdentityList);

    CoreIdentityResponse coreIdentityToCoreIdentityResponse(CoreIdentity coreIdentity);
}
