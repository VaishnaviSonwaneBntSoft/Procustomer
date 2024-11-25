package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.DemographicDataResponseMessage;
import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;
import inc.nimbuspay.procustomer.service.DemographicDataService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/demographic-data")
public class DemographicDataController {
    private static final Logger logger = LoggerFactory.getLogger(DemographicDataController.class);

    private final DemographicDataService demographicDataService;

    public DemographicDataController(DemographicDataService demographicDataService) {
        this.demographicDataService = demographicDataService;
    }

    @PostMapping
    public ResponseEntity<DemographicDataResponse> createDemographicData(@Valid @RequestBody DemographicDataRequest demographicDataRequest) {
        logger.info("Demographic Data create request received {}:", demographicDataRequest);
        DemographicDataResponse creationResponse = demographicDataService.createDemographicData(demographicDataRequest);
        logger.info("Demographic Data created {}", creationResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity<List<DemographicDataResponse>> getAllDemographicData() {
        List<DemographicDataResponse> demographicDataList = demographicDataService.getAllCustomerDemographicData();
        logger.info("Demographic Data Information Fetched for all customers : {}", demographicDataList);
        return ResponseEntity.ok(demographicDataList);
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<DemographicDataResponse> getDemographicData(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching demographic data information request received for customerNumber: {}", customerNumber);
        DemographicDataResponse demographicData = demographicDataService.getDemographicData(customerNumber);
        logger.info("Demographic Data Information Fetched For customerNumber : {}", demographicData);
        return ResponseEntity.status(HttpStatus.FOUND).body(demographicData);
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updateDemographicData(@Valid @PathVariable("customer-number") Long customerNumber,
                                                        @RequestBody DemographicDataRequest updatedDemographicData) {
        logger.info("Demographic data update request received for customer number {} with demographic data details : {}",
                customerNumber, updatedDemographicData);
        demographicDataService.updateDemographicData(updatedDemographicData, customerNumber);
        logger.info("Demographic data is updated for customer number: {}", customerNumber);
        return ResponseEntity.ok()
                .body(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_UPDATED.getMessage(customerNumber));
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deleteDemographicData(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Demographic data delete Request received for customer number: {}", customerNumber);
        demographicDataService.deleteDemographicData(customerNumber);
        logger.info("Demographic data is deleted for customer number: {}", customerNumber);
        return ResponseEntity.ok()
                .body(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_DELETED.getMessage(customerNumber));
    }
}
