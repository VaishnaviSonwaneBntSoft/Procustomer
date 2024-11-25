package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.PhoneNumberResponseMessage;
import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;
import inc.nimbuspay.procustomer.service.PhoneNumberService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/phone-number")
public class PhoneNumberController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberController.class);

    private final PhoneNumberService phoneNumberService;

    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @PostMapping
    ResponseEntity<PhoneNumberResponse> createPhoneNumber(@Valid @RequestBody PhoneNumberRequest phoneNumberRequest) {
        logger.info("Phone number create request received {}:", phoneNumberRequest);
        PhoneNumberResponse creationResponse = phoneNumberService.createPhoneNumber(phoneNumberRequest);
        logger.info("Phone number created {}", creationResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(creationResponse);
    }

    @GetMapping
    ResponseEntity<List<PhoneNumberResponse>> getAllPhoneNumber() {
        List<PhoneNumberResponse> phoneNumberList = phoneNumberService.getAllCustomerPhoneNumber();
        logger.info("Phone number Information Fetched for all customers : {}", phoneNumberList);
        return ResponseEntity.ok(phoneNumberList);
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<PhoneNumberResponse> getPhoneNumber(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching phone number information request received for customerNumber: {}", customerNumber);
        PhoneNumberResponse phoneNumberResponse = phoneNumberService.getPhoneNumber(customerNumber);
        logger.info("Phone number Information Fetched For customerNumber : {}", customerNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(phoneNumberResponse);
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updatePhoneNumber(@Valid @RequestBody PhoneNumberRequest updatePhoneNumberRequest,
                                                    @PathVariable("customer-number") Long customerNumber) {
        logger.info("Phone number update request received for customer number {} with phone number details : {}",
                customerNumber, updatePhoneNumberRequest);
        phoneNumberService.updatePhoneNumber(updatePhoneNumberRequest, customerNumber);
        logger.info("Phone number is updated for customer number: {}", customerNumber);
        return ResponseEntity.ok().
                body(PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_UPDATED.getMessage(customerNumber));
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deletePhoneNumber(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Phone number delete Request received for customer number: {}", customerNumber);
        phoneNumberService.deletePhoneNumber(customerNumber);
        logger.info("Phone number is deleted for customer number: {}", customerNumber);
        return ResponseEntity.ok()
                .body(PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_DELETED.getMessage(customerNumber));
    }
}
