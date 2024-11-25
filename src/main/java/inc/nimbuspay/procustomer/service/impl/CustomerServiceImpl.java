package inc.nimbuspay.procustomer.service.impl;

import java.util.List;

import inc.nimbuspay.procustomer.repository.CustomerCustomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import inc.nimbuspay.procustomer.constant.enums.CustomerResponseMessage;
import inc.nimbuspay.procustomer.exception.CustomerResponseException;
import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;
import inc.nimbuspay.procustomer.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerCustomRepository customerDetailsRepository;
    
    public CustomerServiceImpl(CustomerCustomRepository customerDetailsRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
    }

    @Override
    public CustomerDetailsResponse getAllCustomerDetails(Long customerNumber) {
        String workFlow = "CustomerServiceImpl.getAllCustomerDetails";
        CustomerDetailsResponse response = customerDetailsRepository.findCustomerDetailsByCustomerNumber(customerNumber);
        
        if (response.getCoreIdentity() == null) {
            throw new CustomerResponseException(
                    CustomerResponseMessage.CUSTOMER_DETAILS_NOT_FOUND.getMessage(customerNumber), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                    workFlow);
        }
        return response;
    }

    @Override
    public List<CustomerDetailsResponse> getCustomerDetailsBySearchCriteria(CustomerSearchCriteria customerSearchCriteria) {
        String workFlow = "CustomerServiceImpl.getCustomerDetailsBySearchCriteria";
        List<CustomerDetailsResponse> response = customerDetailsRepository.findCustomerDetailsBySearchCriteria(customerSearchCriteria);

        if (response.isEmpty()) {
            throw new CustomerResponseException(
                    CustomerResponseMessage.CUSTOMER_DETAILS_NOT_EXITS.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,
                    workFlow);
        }
        return response;
    }
}
