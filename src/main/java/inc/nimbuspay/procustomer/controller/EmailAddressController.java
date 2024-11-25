package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.EmailAddressResponseMessage;
import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;
import inc.nimbuspay.procustomer.service.EmailAddressService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/email-address")
public class EmailAddressController {
    private static final Logger logger = LoggerFactory.getLogger(EmailAddressController.class);

    private final EmailAddressService emailAddressService;

    public EmailAddressController(EmailAddressService emailAddressService) {
        this.emailAddressService = emailAddressService;
    }

    @PostMapping
    public ResponseEntity<EmailAddressResponse> createEmailAddress(@Valid @RequestBody EmailAddressRequest emailAddressRequest) {
        logger.info("Email Address create request received {}", emailAddressRequest);
        EmailAddressResponse creationResponse = emailAddressService.createEmailAddress(emailAddressRequest);
        logger.info("Email Address created {}", creationResponse);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(creationResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmailAddressResponse>> getAllEmailAddress() {
        List<EmailAddressResponse> emailAddressesList = emailAddressService.getAllCustomerEmailAddress();
        logger.info("EmailAddress Information Fetched for all customers : {}", emailAddressesList);
        return ResponseEntity.ok(emailAddressesList);
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<EmailAddressResponse> getEmailAddress(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching email address information request received for customerNumber : {}", customerNumber);
        EmailAddressResponse emailAddress = emailAddressService.getEmailAddress(customerNumber);
        logger.info("EmailAddress Information Fetched For customerNumber : {}", emailAddress);
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(emailAddress);
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updateEmailAddress(@PathVariable("customer-number") Long customerNumber,
                                                     @RequestBody EmailAddressRequest updateEmailAddressRequest) {
        logger.info("Email address update request received for customer number {} with email address details : {}",
                customerNumber, updateEmailAddressRequest);
        emailAddressService.updateEmailAddress(updateEmailAddressRequest, customerNumber);
        return ResponseEntity.ok().body(EmailAddressResponseMessage.EMAIL_ADDRESS_SUCCESSFULLY_UPDATED.getMessage(customerNumber));
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deleteEmailAddress(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Email Address delete request received for customer number: {}", customerNumber);
        emailAddressService.deleteEmailAddress(customerNumber);
        logger.info("Email Address is deleted for customer number: {}", customerNumber);
        return ResponseEntity.ok().body(EmailAddressResponseMessage.EMAIL_ADDRESS_SUCCESSFULLY_DELETED.getMessage(customerNumber));
    }
}
