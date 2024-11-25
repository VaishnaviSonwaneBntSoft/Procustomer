package inc.nimbuspay.procustomer.repository;


import java.util.List;

import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;

public interface CustomerCustomRepository {
    CustomerDetailsResponse findCustomerDetailsByCustomerNumber(Long customerNumber);
    List<CustomerDetailsResponse> findCustomerDetailsBySearchCriteria(CustomerSearchCriteria customerSearchCriteria);
}   
