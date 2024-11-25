package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.NationalIdentityResponseMessage;
import inc.nimbuspay.procustomer.entity.NationalIdentity;
import inc.nimbuspay.procustomer.exception.NationalIdentityException;
import inc.nimbuspay.procustomer.mapper.NationalIdentityMapper;
import inc.nimbuspay.procustomer.repository.NationalIdentityRepository;
import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;
import inc.nimbuspay.procustomer.service.NationalIdentityService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NationalIdentityServiceImpl implements NationalIdentityService {
    private static final Logger logger = LoggerFactory.getLogger(NationalIdentityServiceImpl.class);

    private final NationalIdentityRepository nationalIdentityRepository;

    public NationalIdentityServiceImpl(NationalIdentityRepository nationalIdentityRepository) {
        this.nationalIdentityRepository = nationalIdentityRepository;
    }

    @Override
    public NationalIdentityResponse createNationalIdentity(NationalIdentityRequest nationalIdentityRequest) {
        String workflow = "NationalIdentityServiceImpl.getNationalIdentity";

        Long customerNumber = nationalIdentityRequest.getCustomerNumber();

        boolean isNationalIdentityPresent = nationalIdentityRepository.existsByCustomerNumber(customerNumber);
        if (isNationalIdentityPresent) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_EXISTS.getMessageFormat(),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workflow);
        }
        try {
            NationalIdentity nationalIdentity = NationalIdentityMapper.INSTANCE
                    .nationalIdentityRequestToNationalIdentity(nationalIdentityRequest);
                    nationalIdentity.setTimestamp(LocalDateTime.now());
            NationalIdentity createdNationalIdentity = nationalIdentityRepository.save(nationalIdentity);
            return NationalIdentityMapper.INSTANCE.nationalIdentityToNationalIdentityResponse(createdNationalIdentity);
        } catch (Exception ex) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.FAILED_TO_SAVE_NATIONAL_IDENTITY.getMessageFormat(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workflow);
        }
    }

    public List<NationalIdentityResponse> getAllCustomerNationalIdentity() {
        String workflow = "NationalIdentityServiceImpl.getAllCustomerNationalIdentity";

        List<NationalIdentity> nationalIdentities = nationalIdentityRepository.findAll();
        if (nationalIdentities.isEmpty()) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_EXISTS.getMessageFormat(),
                    HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, workflow);
        }

        return NationalIdentityMapper.INSTANCE.nationalIdentityListToNationalIdentityResponseList(nationalIdentities);
    }

    @Override
    public NationalIdentityResponse getNationalIdentity(Long customerNumber) {
        String workflow = "NationalIdentityServiceImpl.getNationalIdentity";

        NationalIdentity nationalIdentity = nationalIdentityRepository.findByCustomerNumber(customerNumber);
        if (nationalIdentity == null) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        return NationalIdentityMapper.INSTANCE.nationalIdentityToNationalIdentityResponse(nationalIdentity);
    }

    @Override
    public void updateNationalIdentity(NationalIdentityRequest updateNationalIdentityRequest, Long customerNumber) {
        String workflow = "NationalIdentityServiceImpl.updateNationalIdentity";

        NationalIdentityResponse existingNationalIdentity = getNationalIdentity(customerNumber);
        if (existingNationalIdentity == null) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        NationalIdentity updateNationalIdentity = NationalIdentityMapper.INSTANCE
                .nationalIdentityRequestToNationalIdentity(updateNationalIdentityRequest);
        updateNationalIdentity.setNationalId(existingNationalIdentity.getNationalId());
        updateNationalIdentity.setCustomerNumber(customerNumber);
        updateNationalIdentity.setDeleted(existingNationalIdentity.isDeleted());

        NationalIdentity updatedNationalIdentity = nationalIdentityRepository.save(updateNationalIdentity);
        logger.info("Updated national identity {}", updatedNationalIdentity);
    }

    @Override
    @Transactional
    public void deleteNationalIdentity(Long customerNumber) {
        String workflow = "NationalIdentityServiceImpl.deleteNationalIdentity";

        NationalIdentity existingNationalIdentity = nationalIdentityRepository.findByCustomerNumber(customerNumber);
        if (existingNationalIdentity == null) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }
        if (existingNationalIdentity.isDeleted()) {
            throw new NationalIdentityException(
                    NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_DELETED.getMessage(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workflow);
        }
        nationalIdentityRepository.softDeleteByCustomerNumber(customerNumber);
    }
}
