package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.PhoneNumber;
import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneNumberMapper {
    PhoneNumberMapper INSTANCE = Mappers.getMapper(PhoneNumberMapper.class);

    PhoneNumber phoneNumberRequestToPhoneNumber(PhoneNumberRequest phoneNumberRequest);

    List<PhoneNumberResponse> phoneNumberListToPhoneNumberResponseList(List<PhoneNumber> phoneNumberList);

    PhoneNumberResponse phoneNumberToPhoneNumberResponse(PhoneNumber phoneNumber);
}
