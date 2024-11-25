package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.DemographicDataResponseMessage;
import inc.nimbuspay.procustomer.entity.DemographicData;
import inc.nimbuspay.procustomer.exception.DemographicDataException;
import inc.nimbuspay.procustomer.mapper.DemographicDataMapper;
import inc.nimbuspay.procustomer.repository.DemographicDataRepository;
import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;
import inc.nimbuspay.procustomer.service.DemographicDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DemographicDataServiceImpl implements DemographicDataService {
    private static final Logger logger = LoggerFactory.getLogger(DemographicDataServiceImpl.class);

    private final DemographicDataRepository demographicDataRepository;

    public DemographicDataServiceImpl(DemographicDataRepository demographicDataRepository) {
        this.demographicDataRepository = demographicDataRepository;
    }

    @Override
    public DemographicDataResponse createDemographicData(DemographicDataRequest demographicDataRequest) {
        String workflow = "DemographicDataServiceImpl.createDemographicData";

        Long customerNumber = demographicDataRequest.getCustomerNumber();

        boolean isDemographicDataPresent = demographicDataRepository.existsByCustomerNumber(customerNumber);
        if (isDemographicDataPresent) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_ALREADY_EXISTS.getMessage(customerNumber),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workflow);
        }
        try {
            DemographicData demographicData = DemographicDataMapper.INSTANCE
                    .demographicDataRequestToDemographicData(demographicDataRequest);
            demographicData.setTimestamp(LocalDateTime.now());
                    DemographicData createdDemographicData = demographicDataRepository.save(demographicData);
            return DemographicDataMapper.INSTANCE.demographicDataToDemographicDataResponse(createdDemographicData);
        } catch (Exception ex) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.FAILED_TO_SAVE_DEMOGRAPHIC_DATA.getMessage(customerNumber),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, workflow);
        }
    }

    @Override
    public List<DemographicDataResponse> getAllCustomerDemographicData() {
        String workflow = "DemographicDataServiceImpl.getAllCustomerDemographicData";

        List<DemographicData> demographicDataList = demographicDataRepository.findAll();
        if (demographicDataList.isEmpty()) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_EXISTS.getMessage(),
                    HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, workflow);
        }

        return DemographicDataMapper.INSTANCE.demographicDataListToDemographicDataResponseList(demographicDataList);
    }

    @Override
    public DemographicDataResponse getDemographicData(Long customerNumber) {
        String workflow = "DemographicDataServiceImpl.getDemographicData";

        DemographicData demographicData = demographicDataRepository.findByCustomerNumber(customerNumber);
        if (demographicData == null) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        return DemographicDataMapper.INSTANCE.demographicDataToDemographicDataResponse(demographicData);
    }

    @Override
    public void updateDemographicData(DemographicDataRequest updateDemographicDataRequest, Long customerNumber) {
        String workflow = "DemographicDataServiceImpl.updateDemographicData";

        DemographicDataResponse existingDemographicData = getDemographicData(customerNumber);
        if (existingDemographicData == null) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }

        DemographicData updateDemographicData = DemographicDataMapper.INSTANCE
                .demographicDataRequestToDemographicData(updateDemographicDataRequest);
        updateDemographicData.setDemographicId(existingDemographicData.getDemographicId());
        updateDemographicData.setCustomerNumber(existingDemographicData.getCustomerNumber());
        updateDemographicData.setDeleted(existingDemographicData.isDeleted());

        DemographicData updatedDemographicData = demographicDataRepository.save(updateDemographicData);
        logger.info("Updated demographic data {}", updatedDemographicData);
    }

    @Override
    @Transactional
    public void deleteDemographicData(Long customerNumber) {
        String workflow = "DemographicDataServiceImpl.deleteDemographicData";

        DemographicData existingDemographicData = demographicDataRepository.findByCustomerNumber(customerNumber);
        if (existingDemographicData == null) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(customerNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workflow);
        }
        if (existingDemographicData.isDeleted()) {
            throw new DemographicDataException(
                    DemographicDataResponseMessage.DEMOGRAPHIC_DATA_ALREADY_DELETED.getMessage(customerNumber),
                    HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE, workflow);
        }
        demographicDataRepository.softDeleteByCustomerNumber(customerNumber);
    }
}
