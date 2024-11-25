package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.exception.CustomerResponseException;
import inc.nimbuspay.procustomer.repository.CustomerCustomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import inc.nimbuspay.procustomer.constant.enums.CustomerResponseMessage;
import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerCustomRepository customerDetailsRepository;

    @InjectMocks
    private CustomerServiceImpl customerDetailsServiceImpl;

    @Test
    void getAllCustomerDetailsSuccess() {
        CustomerDetailsResponse mockResponse = new CustomerDetailsResponse();
        Long customerNumber = 12345L;
        mockResponse.setCoreIdentity(new CoreIdentity()); 
        when(customerDetailsRepository.findCustomerDetailsByCustomerNumber(customerNumber)).thenReturn(mockResponse);
        CustomerDetailsResponse result = customerDetailsServiceImpl.getAllCustomerDetails(customerNumber);
        assertNotNull(result);
        assertEquals(mockResponse, result);
        verify(customerDetailsRepository, times(1)).findCustomerDetailsByCustomerNumber(customerNumber);
    }

    @Test
    void getAllCustomerDetailsCustomerDetailsNotFound() {
        CustomerDetailsResponse mockResponse = new CustomerDetailsResponse();
        Long customerNumber = 12345L;
        mockResponse.setCoreIdentity(null); 
        when(customerDetailsRepository.findCustomerDetailsByCustomerNumber(customerNumber)).thenReturn(mockResponse);
        CustomerResponseException exception = assertThrows(CustomerResponseException.class, () -> {
            customerDetailsServiceImpl.getAllCustomerDetails(customerNumber);
        });
        assertEquals(CustomerResponseMessage.CUSTOMER_DETAILS_NOT_FOUND.getMessage(customerNumber), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(customerDetailsRepository, times(1)).findCustomerDetailsByCustomerNumber(customerNumber);
    }

    @Test
    void getCustomerDetailsBySearchCriteria_Success() {
        CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria(); 
        List<CustomerDetailsResponse> mockResponse = List.of(new CustomerDetailsResponse()); 
        when(customerDetailsRepository.findCustomerDetailsBySearchCriteria(customerSearchCriteria)).thenReturn(mockResponse);
        List<CustomerDetailsResponse> result = customerDetailsServiceImpl.getCustomerDetailsBySearchCriteria(customerSearchCriteria);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(mockResponse, result);
        verify(customerDetailsRepository, times(1)).findCustomerDetailsBySearchCriteria(customerSearchCriteria);
    }

    @Test
    void getCustomerDetailsBySearchCriteriaCustomerDetailsNotExists() {
        CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();  
        when(customerDetailsRepository.findCustomerDetailsBySearchCriteria(customerSearchCriteria)).thenReturn(Collections.emptyList());
        CustomerResponseException exception = assertThrows(CustomerResponseException.class, () -> {
            customerDetailsServiceImpl.getCustomerDetailsBySearchCriteria(customerSearchCriteria);
        });
        assertEquals(CustomerResponseMessage.CUSTOMER_DETAILS_NOT_EXITS.getMessage(), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(customerDetailsRepository, times(1)).findCustomerDetailsBySearchCriteria(customerSearchCriteria);
    }
}
