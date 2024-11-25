package inc.nimbuspay.procustomer.controller;

import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;
import inc.nimbuspay.procustomer.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customer-number}")
    public ResponseEntity<CustomerDetailsResponse> getCustomerDetails(
            @PathVariable("customer-number") Long customerNumber) {
        logger.info("Customer Details retrieval request received for Customer number : {}", customerNumber);
        CustomerDetailsResponse customerDataResponse = customerService.getAllCustomerDetails(customerNumber);
        logger.info("Customer Details : {}", customerDataResponse);
        return ResponseEntity.ok().body(customerDataResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomerDetailsResponse>> getCustomerDetailsBySearchCriteria(
             @RequestBody CustomerSearchCriteria customerSearchCriteria) {
        logger.info("Customer search request received with search fields : {}", customerSearchCriteria);
        List<CustomerDetailsResponse> customerDetailsResponse =
                customerService.getCustomerDetailsBySearchCriteria(customerSearchCriteria);
        logger.info("Customer Details on searched criteria : {}", customerDetailsResponse);
        return ResponseEntity.ok().body(customerDetailsResponse);
    }
}
