package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.CoreIdentityResponseMessage;
import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.exception.CoreIdentityException;
import inc.nimbuspay.procustomer.mapper.CoreIdentityMapper;
import inc.nimbuspay.procustomer.repository.CoreIdentityRepository;
import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;
import inc.nimbuspay.procustomer.service.CoreIdentityService;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreIdentityServiceImpl implements CoreIdentityService {
    private static final Logger logger = LoggerFactory.getLogger(CoreIdentityServiceImpl.class);

    private final CoreIdentityRepository coreIdentityRepository;
    

    public CoreIdentityServiceImpl(CoreIdentityRepository coreIdentityRepository) {
        this.coreIdentityRepository = coreIdentityRepository;
    }

    @Override
    public CoreIdentityResponse createCoreIdentity(CoreIdentityRequest coreIdentityRequest) {
        String workFlow = "CoreIdentityServiceImpl.createCoreIdentity";

        Long customerNumber = coreIdentityRequest.getCustomerNumber();

        boolean isCoreIdentityPresent = coreIdentityRepository.existsByCustomerNumber(customerNumber);
        if (isCoreIdentityPresent) {
            throw new CoreIdentityException(CoreIdentityResponseMessage.CORE_IDENTITY_ALREADY_EXISTS.getMessage(customerNumber),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
        try {
            CoreIdentity coreIdentity = CoreIdentityMapper.INSTANCE
                    .coreIdentityRequestToCoreIdentity(coreIdentityRequest);
            coreIdentity.setTimestamp(LocalDateTime.now());
                    CoreIdentity createdCoreIdentity = coreIdentityRepository.save(coreIdentity);
            return CoreIdentityMapper.INSTANCE.coreIdentityToCoreIdentityResponse(createdCoreIdentity);
        } catch (CoreIdentityException ex) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.FAILED_TO_SAVE_CORE_IDENTITY.getMessage(customerNumber),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workFlow);
        }
    }

    @Override
    public List<CoreIdentityResponse> getAllCoreIdentity() {
        String workFlow = "CoreIdentityServiceImpl.getAllCoreIdentities";

        List<CoreIdentity> coreIdentityList = coreIdentityRepository.findAll();
        if (coreIdentityList.isEmpty()) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.CORE_IDENTITY_NOT_EXISTS.getMessage(),
                    HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, workFlow);
        }
        return CoreIdentityMapper.INSTANCE.coreIdentityListToCoreIdentityResponseList(coreIdentityList);
    }

    @Override
    public CoreIdentityResponse getCoreIdentity(Long customerNumber) {
        String workFlow = "CoreIdentityServiceImpl.getCoreIdentity";

        CoreIdentity coreIdentity = coreIdentityRepository.findByCustomerNumber(customerNumber);
        if (coreIdentity == null) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.CORE_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        return CoreIdentityMapper.INSTANCE.coreIdentityToCoreIdentityResponse(coreIdentity);
    }

    @Override
    public void updateCoreIdentity(CoreIdentityRequest updateCoreIdentityRequest, Long customerNumber) {
        String workFlow = "CoreIdentityServiceImpl.updateCoreIdentity";

        CoreIdentityResponse existingCoreIdentity = getCoreIdentity(customerNumber);
        if (existingCoreIdentity == null) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.CORE_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }

        CoreIdentity updateCoreIdentity = CoreIdentityMapper.INSTANCE
                .coreIdentityRequestToCoreIdentity(updateCoreIdentityRequest);
        updateCoreIdentity.setConsumerId(existingCoreIdentity.getConsumerId());
        updateCoreIdentity.setCustomerNumber(existingCoreIdentity.getCustomerNumber());
        updateCoreIdentity.setDeleted(existingCoreIdentity.isDeleted());

        CoreIdentity updatedCoreIdentity = coreIdentityRepository.save(updateCoreIdentity);
        logger.info("Updated core identity {}", updatedCoreIdentity);
    }

    @Transactional
    @Override
    public void deleteCoreIdentity(Long customerNumber) {
        String workFlow = "CoreIdentityServiceImpl.deleteCoreIdentity";

        CoreIdentity existingCoreIdentity = coreIdentityRepository.findByCustomerNumber(customerNumber);
        if (existingCoreIdentity == null) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.CORE_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        if (existingCoreIdentity.isDeleted()) {
            throw new CoreIdentityException(
                    CoreIdentityResponseMessage.CORE_IDENTITY_ALREADY_DELETED.getMessage(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workFlow);
        }
        coreIdentityRepository.softDeleteByCustomerNumber(customerNumber);
    }

    
}
