package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.NationalIdentityResponseMessage;
import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;
import inc.nimbuspay.procustomer.service.NationalIdentityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/national-identity")
public class NationalIdentityController {
    private static final Logger logger = LoggerFactory.getLogger(NationalIdentityController.class);

    private final NationalIdentityService nationalIdentityService;

    public NationalIdentityController(NationalIdentityService nationalIdentityService) {
        this.nationalIdentityService = nationalIdentityService;
    }

    @PostMapping
    public ResponseEntity<NationalIdentityResponse> createNationalIdentity(@Valid @RequestBody NationalIdentityRequest nationalIdentityRequest) {
        logger.info("National identity Create Request received for: {}", nationalIdentityRequest);
        NationalIdentityResponse response = nationalIdentityService.createNationalIdentity(nationalIdentityRequest);
        logger.info("National identity is created {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<NationalIdentityResponse>> getNationalIdentities() {
        List<NationalIdentityResponse> response = nationalIdentityService.getAllCustomerNationalIdentity();
        logger.info("Fetched all National identities: {}", response);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{customer-number}")
    public ResponseEntity<NationalIdentityResponse> getNationalIdenEntity(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching national identity information request received for customerNumber: {}", customerNumber);
        NationalIdentityResponse response = nationalIdentityService.getNationalIdentity(customerNumber);
        logger.info("Fetched national identity information for customerNumber: {}", response);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deleteNationalIdentity(@PathVariable("customer-number") Long customerNumber) {
        logger.info("National identity Delete Request received for customer number: {}", customerNumber);
        nationalIdentityService.deleteNationalIdentity(customerNumber);
        logger.info("National identity is Deleted for customer number: {}", customerNumber);
        return ResponseEntity.ok()
                .body(NationalIdentityResponseMessage.NATIONAL_IDENTITY_SUCCESSFULLY_DELETED.getMessage(customerNumber));
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updateNationalIdentity(@Valid @PathVariable("customer-number") Long customerNumber,
                                                         @RequestBody NationalIdentityRequest updatedNationalIdentity) {
        logger.info("National identity update Request received for customer: {} {}", customerNumber,
                updatedNationalIdentity);
        nationalIdentityService.updateNationalIdentity(updatedNationalIdentity, customerNumber);
        return ResponseEntity.ok()
                .body(NationalIdentityResponseMessage.NATIONAL_IDENTITY_SUCCESSFULLY_UPDATED.getMessage(customerNumber));
    }
}
