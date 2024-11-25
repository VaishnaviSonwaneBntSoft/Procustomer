package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.MailAddress;
import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MailAddressMapper {
    MailAddressMapper INSTANCE = Mappers.getMapper(MailAddressMapper.class);

    MailAddressResponse mailAddressToMailAddressResponse(MailAddress mailAddress);

    List<MailAddressResponse> mailAddressListToMailAddressResponseList(List<MailAddress> mailAddressList);

    MailAddress mailAddressRequestToMailAddress(MailAddressRequest mailAddressRequest);
}
