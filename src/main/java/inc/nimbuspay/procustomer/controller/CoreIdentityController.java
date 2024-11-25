package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.CoreIdentityResponseMessage;
import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;
import inc.nimbuspay.procustomer.service.CoreIdentityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/core-identity")
public class CoreIdentityController {
    private static final Logger logger = LoggerFactory.getLogger(CoreIdentityController.class);

    private final CoreIdentityService coreIdentityService;

    public CoreIdentityController(CoreIdentityService coreIdentityService) {
        this.coreIdentityService = coreIdentityService;
    }

    @PostMapping
    public ResponseEntity<CoreIdentityResponse> createCoreIdentity(@Valid @RequestBody CoreIdentityRequest coreIdentity) {
        logger.info("Core Identity create request received {}", coreIdentity);
        CoreIdentityResponse creationResponse = coreIdentityService.createCoreIdentity(coreIdentity);
        logger.info("Core Identity created {}", creationResponse);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity<List<CoreIdentityResponse>> getAllCoreIdentities() {
        logger.info("Core Identity list retrieval request received");
        List<CoreIdentityResponse> coreIdentityResponseList = coreIdentityService.getAllCoreIdentity();
        logger.info("All Core Identities fetched {} :", coreIdentityResponseList);
        return new ResponseEntity<>(coreIdentityResponseList, HttpStatus.OK);
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<CoreIdentityResponse> getCoreIdentity(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching coreIdentity Information received for customerNumber : {}", customerNumber);
        CoreIdentityResponse coreIdentity = coreIdentityService.getCoreIdentity(customerNumber);
        logger.info("Information Fetched For accountNumber : {}", coreIdentity);
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(coreIdentity);
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updateCoreIdentity(@PathVariable("customer-number") Long customerNumber,
                                                     @RequestBody CoreIdentityRequest updateCoreIdentity) {
        logger.info("Core Identity update request received for customer number {} with core identity details : {}",
                customerNumber, updateCoreIdentity);
        coreIdentityService.updateCoreIdentity(updateCoreIdentity, customerNumber);
        return ResponseEntity.ok()
                .body(CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_UPDATED.getMessage(customerNumber));
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deleteCoreIdentity(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Core Identity delete request received for customer number: {}", customerNumber);
        coreIdentityService.deleteCoreIdentity(customerNumber);
        logger.info("Core Identity is deleted for customer number: {}", customerNumber);
        return ResponseEntity.ok()
                .body(CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_DELETED.getMessage(customerNumber));
    }

}
