package inc.nimbuspay.procustomer.repository.impl;

import java.util.ArrayList;
import java.util.List;

import inc.nimbuspay.procustomer.exception.CustomerResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import inc.nimbuspay.procustomer.constant.enums.CustomerResponseMessage;
import inc.nimbuspay.procustomer.constant.query.CustomerQueryConstant;
import inc.nimbuspay.procustomer.repository.CustomerCustomRepository;
import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository {

    private final EntityManager entityManager;

    public CustomerCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CustomerDetailsResponse findCustomerDetailsByCustomerNumber(Long customerNumber) {
        String workFlow = "CustomerCustomRepositoryImpl.findCustomerDetailsByCustomerNumber";

        Query query = entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER);
        query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerNumber);
        try{
            return (CustomerDetailsResponse)query.getSingleResult();
        }
        catch(CustomerResponseException exception){
            throw new CustomerResponseException(CustomerResponseMessage.FAILED_TO_FETCH_CUSTOMER_DETAILS.getMessage(customerNumber), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT , workFlow );
        }
    }

    public List<CustomerDetailsResponse> findCustomerDetailsBySearchCriteria(CustomerSearchCriteria customerSearchCriteria) {
        String workFlow = "CustomerCustomRepositoryImpl.findCustomerDetailsBySearchCriteria";
    
        List<CustomerDetailsResponse> customerDetailsResponseList = new ArrayList<>();
    
        TypedQuery<CustomerDetailsResponse> query = entityManager.createQuery(CustomerQueryConstant.GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA, CustomerDetailsResponse.class);
    
        try {
            query.setParameter(CustomerQueryConstant.CUSTOMER_NUMBER, customerSearchCriteria.getCustomerNumber());
            query.setParameter(CustomerQueryConstant.FIRST_NAME,customerSearchCriteria.getFirstName());
            query.setParameter(CustomerQueryConstant.FAMILY_SURNAME, customerSearchCriteria.getFamilySurname());
            query.setParameter(CustomerQueryConstant.EMAIL_ADDRESS_DATA, customerSearchCriteria.getEmailAddressData());
            query.setParameter(CustomerQueryConstant.PRIMARY_PHONE_NUMBER, customerSearchCriteria.getPrimaryPhoneNumber());
            query.setParameter(CustomerQueryConstant.PASS_PORT_NUMBER, customerSearchCriteria.getPassportNumber());
            query.setParameter(CustomerQueryConstant.DRIVING_LICENSE_NUMBER, customerSearchCriteria.getDrivingLicenseNumber());
    
            customerDetailsResponseList = query.getResultList();
        } catch (Exception exception) {
            throw new CustomerResponseException(CustomerResponseMessage.FAILED_TO_FETCH_CUSTOMER_DETAILS.getMessage(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
    
        return customerDetailsResponseList;
    }
}
