package inc.nimbuspay.procustomer.repository.impl;

import inc.nimbuspay.procustomer.exception.CustomerResponseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import inc.nimbuspay.procustomer.constant.enums.CustomerResponseMessage;
import inc.nimbuspay.procustomer.constant.query.CustomerQueryConstant;
import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerCustomRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CustomerCustomRepositoryImpl repository;

    @Test
    void testFindCustomerDetailsByCustomerNumber_Success() {
        Long customerNumber = 123L;
        CustomerDetailsResponse expectedResponse = new CustomerDetailsResponse();
        Query query = mock(Query.class);
        when(entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER)).thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerNumber)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedResponse);
        CustomerDetailsResponse result = repository.findCustomerDetailsByCustomerNumber(customerNumber);
        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(entityManager, times(1)).createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER);
        verify(query, times(1)).setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerNumber);
        verify(query, times(1)).getSingleResult();
    }

    @Test
    void testFindCustomerDetailsByCustomerNumber_Exception() {
        Long customerNumber = 123L;
        Query query = mock(Query.class);
        when(entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER)).thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerNumber)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new CustomerResponseException(
                CustomerResponseMessage.FAILED_TO_FETCH_CUSTOMER_DETAILS.getMessage(customerNumber),
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, "CustomerCustomRepositoryImpl.findCustomerDetailsByCustomerNumber"
        ));
        assertThrows(CustomerResponseException.class, () ->
                repository.findCustomerDetailsByCustomerNumber(customerNumber));
        verify(entityManager, times(1)).createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER);
        verify(query, times(1)).setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerNumber);
        verify(query, times(1)).getSingleResult();
    }

    @Test
    void testFindCustomerDetailsBySearchCriteria_Success() {
        CustomerSearchCriteria criteria = new CustomerSearchCriteria();
        criteria.setCustomerNumber(123L);
        criteria.setFirstName("John");
        criteria.setFamilySurname("Doe");
        criteria.setEmailAddressData("john.doe@example.com");
        criteria.setPrimaryPhoneNumber("123456789");
        criteria.setPassportNumber("AB123456");
        criteria.setDrivingLicenseNumber("DL123456");
        CustomerDetailsResponse response1 = new CustomerDetailsResponse();
        CustomerDetailsResponse response2 = new CustomerDetailsResponse();
        List<CustomerDetailsResponse> expectedResponses = Arrays.asList(response1, response2);
        @SuppressWarnings("unchecked")
        TypedQuery<CustomerDetailsResponse> query = mock(TypedQuery.class);
        when(entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA, CustomerDetailsResponse.class))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, criteria.getCustomerNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.FIRST_NAME, criteria.getFirstName()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.FAMILY_SURNAME, criteria.getFamilySurname()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.EMAIL_ADDRESS_DATA, criteria.getEmailAddressData()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.PRIMARY_PHONE_NUMBER, criteria.getPrimaryPhoneNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.PASS_PORT_NUMBER, criteria.getPassportNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.DRIVING_LICENSE_NUMBER, criteria.getDrivingLicenseNumber()))
                .thenReturn(query);
        when(query.getResultList()).thenReturn(expectedResponses);
        List<CustomerDetailsResponse> result = repository.findCustomerDetailsBySearchCriteria(criteria);
        assertNotNull(result);
        assertEquals(expectedResponses, result);
        verify(entityManager, times(1)).createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA, CustomerDetailsResponse.class);
        verify(query, times(1)).setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, criteria.getCustomerNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.FIRST_NAME, criteria.getFirstName());
        verify(query, times(1)).setParameter(CustomerQueryConstant.FAMILY_SURNAME, criteria.getFamilySurname());
        verify(query, times(1)).setParameter(CustomerQueryConstant.EMAIL_ADDRESS_DATA, criteria.getEmailAddressData());
        verify(query, times(1)).setParameter(CustomerQueryConstant.PRIMARY_PHONE_NUMBER, criteria.getPrimaryPhoneNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.PASS_PORT_NUMBER, criteria.getPassportNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.DRIVING_LICENSE_NUMBER, criteria.getDrivingLicenseNumber());
        verify(query, times(1)).getResultList();
    }

    @Test
    void testFindCustomerDetailsBySearchCriteria_Exception() {
        CustomerSearchCriteria criteria = new CustomerSearchCriteria();
        criteria.setCustomerNumber(123L);
        criteria.setFirstName("John");
        criteria.setFamilySurname("Doe");
        criteria.setEmailAddressData("john.doe@example.com");
        criteria.setPrimaryPhoneNumber("123456789");
        criteria.setPassportNumber("AB123456");
        criteria.setDrivingLicenseNumber("DL123456");
        @SuppressWarnings("unchecked")
        TypedQuery<CustomerDetailsResponse> query = mock(TypedQuery.class);
        when(entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA, CustomerDetailsResponse.class))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, criteria.getCustomerNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.FIRST_NAME, criteria.getFirstName()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.FAMILY_SURNAME, criteria.getFamilySurname()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.EMAIL_ADDRESS_DATA, criteria.getEmailAddressData()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.PRIMARY_PHONE_NUMBER, criteria.getPrimaryPhoneNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.PASS_PORT_NUMBER, criteria.getPassportNumber()))
                .thenReturn(query);
        when(query.setParameter(CustomerQueryConstant.DRIVING_LICENSE_NUMBER, criteria.getDrivingLicenseNumber()))
                .thenReturn(query);
        when(query.getResultList()).thenThrow(new CustomerResponseException(
                CustomerResponseMessage.FAILED_TO_FETCH_CUSTOMER_DETAILS.getMessage(),
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, "CustomerCustomRepositoryImpl.findCustomerDetailsBySearchCriteria"
        ));
        assertThrows(CustomerResponseException.class, () ->
                repository.findCustomerDetailsBySearchCriteria(criteria));
        verify(entityManager, times(1)).createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA, CustomerDetailsResponse.class);
        verify(query, times(1)).setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, criteria.getCustomerNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.FIRST_NAME, criteria.getFirstName());
        verify(query, times(1)).setParameter(CustomerQueryConstant.FAMILY_SURNAME, criteria.getFamilySurname());
        verify(query, times(1)).setParameter(CustomerQueryConstant.EMAIL_ADDRESS_DATA, criteria.getEmailAddressData());
        verify(query, times(1)).setParameter(CustomerQueryConstant.PRIMARY_PHONE_NUMBER, criteria.getPrimaryPhoneNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.PASS_PORT_NUMBER, criteria.getPassportNumber());
        verify(query, times(1)).setParameter(CustomerQueryConstant.DRIVING_LICENSE_NUMBER, criteria.getDrivingLicenseNumber());
        verify(query, times(1)).getResultList();
    }
}

