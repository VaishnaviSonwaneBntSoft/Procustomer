package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.NationalIdentity;
import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NationalIdentityMapper {
    NationalIdentityMapper INSTANCE = Mappers.getMapper(NationalIdentityMapper.class);

    NationalIdentity nationalIdentityRequestToNationalIdentity(NationalIdentityRequest nationalIdentityRequest);

    List<NationalIdentityResponse> nationalIdentityListToNationalIdentityResponseList(List<NationalIdentity> nationalIdentityList);

    NationalIdentityResponse nationalIdentityToNationalIdentityResponse(NationalIdentity nationalIdentity);
}
