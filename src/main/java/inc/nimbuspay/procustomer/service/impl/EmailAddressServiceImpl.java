package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.EmailAddressResponseMessage;
import inc.nimbuspay.procustomer.entity.EmailAddress;
import inc.nimbuspay.procustomer.exception.EmailAddressException;
import inc.nimbuspay.procustomer.mapper.EmailAddressMapper;
import inc.nimbuspay.procustomer.repository.EmailAddressRepository;
import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;
import inc.nimbuspay.procustomer.service.EmailAddressService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailAddressServiceImpl implements EmailAddressService {
    private static final Logger logger = LoggerFactory.getLogger(EmailAddressServiceImpl.class);

    private final EmailAddressRepository emailAddressRepository;

    public EmailAddressServiceImpl(EmailAddressRepository emailAddressRepository) {
        this.emailAddressRepository = emailAddressRepository;
    }

    @Override
    public EmailAddressResponse createEmailAddress(EmailAddressRequest emailAddressRequest) {
        String workFlow = "EmailAddressServiceImpl.createEmailAddress";

        Long customerNumber = emailAddressRequest.getCustomerNumber();

        boolean isEmailAddressPresent = emailAddressRepository.existsByCustomerNumber(customerNumber);
        if (isEmailAddressPresent) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_ALREADY_EXISTS.getMessage(customerNumber),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
        try {
            EmailAddress emailAddress = EmailAddressMapper.INSTANCE.emailAddressRequestToEmailAddress(emailAddressRequest);
            emailAddress.setTimestamp(LocalDateTime.now());
            EmailAddress createdEmailAddress = emailAddressRepository.save(emailAddress);
            return EmailAddressMapper.INSTANCE.emailAddressToEmailAddressResponse(createdEmailAddress);
        } catch (EmailAddressException e) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.FAILED_TO_SAVE_EMAIL_ADDRESS.getMessage(customerNumber),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workFlow);
        }
    }

    @Override
    public List<EmailAddressResponse> getAllCustomerEmailAddress() {
        String workFlow = "EmailAddressServiceImpl.getAllCustomerEmailAddress";

        List<EmailAddress> emailAddressList = emailAddressRepository.findAll();
        if (emailAddressList.isEmpty()) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_NOT_FOUND.getMessage(),
                    HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, workFlow);
        }
        return EmailAddressMapper.INSTANCE.emailAddressListToEmailAddressResponseList(emailAddressList);
    }

    @Override
    public EmailAddressResponse getEmailAddress(Long customerNumber) {
        String workFlow = "EmailAddressServiceImpl.getEmailAddress";

        EmailAddress emailAddress = emailAddressRepository.findByCustomerNumber(customerNumber);
        if (emailAddress == null) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        return EmailAddressMapper.INSTANCE.emailAddressToEmailAddressResponse(emailAddress);
    }

    @Override
    public void updateEmailAddress(EmailAddressRequest updateEmailAddressRequest, Long customerNumber) {
        String workFlow = "EmailAddressServiceImpl.updateEmailAddress";

        EmailAddressResponse existingEmailAddress = getEmailAddress(customerNumber);
        if (existingEmailAddress == null) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        EmailAddress updateEmailAddress = EmailAddressMapper.INSTANCE
                .emailAddressRequestToEmailAddress(updateEmailAddressRequest);
        updateEmailAddress.setEmailId(existingEmailAddress.getEmailId());
        updateEmailAddress.setCustomerNumber(existingEmailAddress.getCustomerNumber());
        updateEmailAddress.setDeleted(existingEmailAddress.isDeleted());

        EmailAddress updatedEmailAddress = emailAddressRepository.save(updateEmailAddress);
        logger.info("Updated email address {}", updatedEmailAddress);
    }

    @Transactional
    @Override
    public void deleteEmailAddress(Long customerNumber) {
        String workFlow = "EmailAddressServiceImpl.deleteEmailAddress";

        EmailAddress existingEmailAddress = emailAddressRepository.findByCustomerNumber(customerNumber);
        if (existingEmailAddress == null) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        if (existingEmailAddress.isDeleted()) {
            throw new EmailAddressException(
                    EmailAddressResponseMessage.EMAIL_ADDRESS_ALREADY_DELETED.getMessage(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workFlow);
        }
        emailAddressRepository.softDeleteByCustomerNumber(customerNumber);
    }
} 
