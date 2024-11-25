package inc.nimbuspay.procustomer.mapper;

import inc.nimbuspay.procustomer.entity.EmailAddress;
import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailAddressMapper {
    EmailAddressMapper INSTANCE = Mappers.getMapper(EmailAddressMapper.class);

    EmailAddress emailAddressRequestToEmailAddress(EmailAddressRequest emailAddressRequest);

    List<EmailAddressResponse> emailAddressListToEmailAddressResponseList(List<EmailAddress> emailAddress);

    EmailAddressResponse emailAddressToEmailAddressResponse(EmailAddress emailAddress);
}
