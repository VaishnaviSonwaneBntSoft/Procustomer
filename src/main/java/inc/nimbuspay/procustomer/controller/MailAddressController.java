package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.constant.enums.MailAddressResponseMessage;
import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;
import inc.nimbuspay.procustomer.service.MailAddressService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/mail-address")
public class MailAddressController {

    private static final Logger logger = LoggerFactory.getLogger(MailAddressController.class);

    private final MailAddressService mailAddressService;

    public MailAddressController(MailAddressService mailAddressService) {
        this.mailAddressService = mailAddressService;
    }

    @PostMapping
    public ResponseEntity<MailAddressResponse> createMailAddress(@Valid @RequestBody MailAddressRequest mailAddressRequest) {
        logger.info("Mail Address create request received : {}", mailAddressRequest);
        MailAddressResponse creationResponse = mailAddressService.createMailAddress(mailAddressRequest);
        logger.info("Mail Address Created : {}", creationResponse);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(creationResponse);
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<MailAddressResponse> getMailAddress(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Fetching mail address request received for account number : {}", customerNumber);
        MailAddressResponse mailAddressResponse = mailAddressService.getMailAddress(customerNumber);
        logger.info("Fetched mail address information for customer: {}", mailAddressResponse);
        return ResponseEntity.status(HttpStatus.FOUND).body(mailAddressResponse);
    }

    @PutMapping("/{customer-number}")
    public ResponseEntity<String> updateMailAddress(@PathVariable("customer-number") Long customerNumber, @Valid @RequestBody MailAddressRequest mailAddressRequest) {
        logger.info("Mail Address update request received for customer number {} with mail address details : {}", customerNumber, mailAddressRequest);
        mailAddressService.updateMailAddress(mailAddressRequest, customerNumber);
        logger.info("Mail Address for customer number {}  updated successfully : {}", customerNumber, mailAddressRequest);
        return ResponseEntity.status(HttpStatus.OK.value()).body(MailAddressResponseMessage.MAIL_ADDRESS_SUCCESSFULLY_UPDATED.getMessageFormat(customerNumber));
    }

    @DeleteMapping("/{customer-number}")
    public ResponseEntity<String> deleteMailAddress(@PathVariable("customer-number") Long customerNumber) {
        logger.info("Mail Address delete request received for customer number {} ", customerNumber);
        mailAddressService.deleteMailAddress(customerNumber);
        logger.info("Mail Address with customer number {} deleted successfully", customerNumber);
        return ResponseEntity.status(HttpStatus.OK.value()).body(MailAddressResponseMessage.MAIL_ADDRESS_SUCCESSFULLY_DELETED.getMessageFormat(customerNumber));
    }

    @GetMapping
    public ResponseEntity<List<MailAddressResponse>> getAllMailAddresses() {
        logger.info("Request received for fetch all mail addresses");
        List<MailAddressResponse> mailAddresses = mailAddressService.getAllCustomerMailAddress();
        logger.info("Successfully retrieved all mail addresses");
        return new ResponseEntity<>(mailAddresses, HttpStatus.OK);
    }
}   

