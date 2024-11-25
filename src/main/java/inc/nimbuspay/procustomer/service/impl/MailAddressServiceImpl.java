package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.MailAddressResponseMessage;
import inc.nimbuspay.procustomer.entity.MailAddress;
import inc.nimbuspay.procustomer.exception.MailAddressException;
import inc.nimbuspay.procustomer.mapper.MailAddressMapper;
import inc.nimbuspay.procustomer.repository.MailAddressRepository;
import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;
import inc.nimbuspay.procustomer.service.MailAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailAddressServiceImpl implements MailAddressService {
    private static final Logger logger = LoggerFactory.getLogger(MailAddressServiceImpl.class);

    private final MailAddressRepository mailAddressRepository;

    public MailAddressServiceImpl(MailAddressRepository mailAddressRepository) {
        this.mailAddressRepository = mailAddressRepository;
    }

    @Override
    public MailAddressResponse createMailAddress(MailAddressRequest mailAddressRequest) {
        String workFlow = "MailAddressService.createMailAddress";

        Long customerNumber = mailAddressRequest.getCustomerNumber();
        boolean isMailAddressPresent = mailAddressRepository.existsByCustomerNumber(customerNumber);
        if (isMailAddressPresent) {
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_ALREADY_EXISTS.getMessageFormat(customerNumber),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
        try {
            MailAddress mailAddress = MailAddressMapper.INSTANCE.mailAddressRequestToMailAddress(mailAddressRequest);
            mailAddress.setTimestamp(LocalDateTime.now());
            MailAddress createdMailAddress = mailAddressRepository.save(mailAddress);
            return MailAddressMapper.INSTANCE.mailAddressToMailAddressResponse(createdMailAddress);
        } catch (MailAddressException ex) {
            throw new MailAddressException(
                    MailAddressResponseMessage.FAILED_TO_SAVE_MAIL_ADDRESS.getMessageFormat(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workFlow);
        }
    }

    @Override
    public List<MailAddressResponse> getAllCustomerMailAddress() {
        String workFlow = "MailAddressServiceImpl.getAllCustomerMailAddress";

        List<MailAddress> mailAddressList = mailAddressRepository.findAll();
        if (mailAddressList.isEmpty()) {
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_NOT_EXISTS.getMessageFormat(),
                    HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, workFlow);
        }
        return MailAddressMapper.INSTANCE.mailAddressListToMailAddressResponseList(mailAddressList);
    }

    @Override
    public MailAddressResponse getMailAddress(Long customerNumber) {
        String workFlow = "MailAddressServiceImpl.getMailAddress";

        MailAddress mailAddress = mailAddressRepository.findByCustomerNumber(customerNumber);
        if (mailAddress == null) {
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_NOT_FOUND.getMessageFormat(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        return MailAddressMapper.INSTANCE.mailAddressToMailAddressResponse(mailAddress);
    }

    @Override
    public void updateMailAddress(MailAddressRequest mailAddressRequest, Long customerNumber) {
        String workFlow = "MailAddressServiceImpl.updateMailAddress";

        MailAddressResponse existingMailAddress = getMailAddress(customerNumber);
        if (existingMailAddress == null) {
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_NOT_FOUND.getMessageFormat(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        MailAddress mailAddress = MailAddressMapper.INSTANCE.mailAddressRequestToMailAddress(mailAddressRequest);
        mailAddress.setAddressId(existingMailAddress.getAddressId());
        mailAddress.setCustomerNumber(existingMailAddress.getCustomerNumber());
        mailAddress.setDeleted(existingMailAddress.isDeleted());

        MailAddress updatedMailAddress = mailAddressRepository.save(mailAddress);
        logger.info("Updated demographic data {}", updatedMailAddress);
    }

    @Override
    @Transactional
    public void deleteMailAddress(Long customerNumber) {
        String workFlow = "MailAddressServiceImpl.deleteMailAddress";

        MailAddress existingMailAddress = mailAddressRepository.findByCustomerNumber(customerNumber);
        if (existingMailAddress == null) {
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_NOT_FOUND.getMessageFormat(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        if (existingMailAddress.isDeleted()) {
            logger.error("Mail address already deleted for customer number: {}", customerNumber);
            throw new MailAddressException(
                    MailAddressResponseMessage.MAIL_ADDRESS_ALREADY_DELETED.getMessageFormat(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workFlow);
        }
        mailAddressRepository.softDeleteByCustomerNumber(customerNumber);
    }
}
