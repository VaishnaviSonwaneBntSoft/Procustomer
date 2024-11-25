package inc.nimbuspay.procustomer.service;

import java.util.List;

import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;

public interface CustomerService {
    
    CustomerDetailsResponse getAllCustomerDetails(Long customerNumber);

    List<CustomerDetailsResponse> getCustomerDetailsBySearchCriteria(CustomerSearchCriteria customerSearchCriteria);
}
