package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.PhoneNumberResponseMessage;
import inc.nimbuspay.procustomer.entity.PhoneNumber;
import inc.nimbuspay.procustomer.exception.PhoneNumberException;
import inc.nimbuspay.procustomer.mapper.PhoneNumberMapper;
import inc.nimbuspay.procustomer.repository.PhoneNumberRepository;
import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;
import inc.nimbuspay.procustomer.service.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberServiceImpl.class);

    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public PhoneNumberResponse createPhoneNumber(PhoneNumberRequest phoneNumberRequest) {
        String workflow = "PhoneNumberServiceImpl.createPhoneNumber";

        Long customerNumber = phoneNumberRequest.getCustomerNumber();

        boolean isPhoneNumberPresent = phoneNumberRepository.existsByCustomerNumber(customerNumber);
        if (isPhoneNumberPresent) {
            throw new PhoneNumberException(PhoneNumberResponseMessage.PHONE_NUMBER_ALREADY_EXISTS.getMessage(customerNumber),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workflow);
        }

        try {
            PhoneNumber phoneNumber = PhoneNumberMapper.INSTANCE.phoneNumberRequestToPhoneNumber(phoneNumberRequest);
            phoneNumber.setTimestamp(LocalDateTime.now());
            PhoneNumber createdPhoneNumber = phoneNumberRepository.save(phoneNumber);
            return PhoneNumberMapper.INSTANCE.phoneNumberToPhoneNumberResponse(createdPhoneNumber);
        } catch (PhoneNumberException e) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.FAILED_TO_SAVE_PHONE_NUMBER.getMessage(customerNumber),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workflow);
        }
    }

    @Override
    public List<PhoneNumberResponse> getAllCustomerPhoneNumber() {
        String workflow = "PhoneNumberServiceImpl.getAllCustomerPhoneNumber";

        List<PhoneNumber> phoneNumberList = phoneNumberRepository.findAll();
        if (phoneNumberList.isEmpty()) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.PHONE_NUMBER_NOT_EXISTS.getMessage(),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        return PhoneNumberMapper.INSTANCE.phoneNumberListToPhoneNumberResponseList(phoneNumberList);
    }

    @Override
    public PhoneNumberResponse getPhoneNumber(Long customerNumber) {
        String workflow = "PhoneNumberServiceImpl.getPhoneNumber";

        Optional<PhoneNumber> existingPhoneNumber = phoneNumberRepository.findByCustomerNumber(customerNumber);
        if (existingPhoneNumber.isEmpty()) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.PHONE_NUMBER_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        return PhoneNumberMapper.INSTANCE.phoneNumberToPhoneNumberResponse(existingPhoneNumber.get());
    }

    @Override
    public void updatePhoneNumber(PhoneNumberRequest phoneNumberRequest, Long customerNumber) {
        String workflow = "PhoneNumberServiceImpl.updatePhoneNumber";

        PhoneNumberResponse existingPhoneNumber = getPhoneNumber(customerNumber);
        if (existingPhoneNumber == null) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.PHONE_NUMBER_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        PhoneNumber updatePhoneNumber = PhoneNumberMapper.INSTANCE.phoneNumberRequestToPhoneNumber(phoneNumberRequest);
        updatePhoneNumber.setPhoneId(existingPhoneNumber.getPhoneId());
        updatePhoneNumber.setCustomerNumber(existingPhoneNumber.getCustomerNumber());
        updatePhoneNumber.setDeleted(existingPhoneNumber.isDeleted());

        PhoneNumber updatedPhoneNumber = phoneNumberRepository.save(updatePhoneNumber);
        logger.info("Updated phone number {}", updatedPhoneNumber);
    }

    @Override
    @Transactional
    public void deletePhoneNumber(Long customerNumber) {
        String workflow = "PhoneNumberServiceImpl.deletePhoneNumber";

        Optional<PhoneNumber> existingPhoneNumber = phoneNumberRepository.findByCustomerNumber(customerNumber);
        if (existingPhoneNumber.isEmpty()) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.PHONE_NUMBER_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }
        if (existingPhoneNumber.get().isDeleted()) {
            throw new PhoneNumberException(
                    PhoneNumberResponseMessage.PHONE_NUMBER_ALREADY_DELETED.getMessage(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workflow);
        }
        phoneNumberRepository.softDeleteByCustomerNumber(customerNumber);
    }
}
