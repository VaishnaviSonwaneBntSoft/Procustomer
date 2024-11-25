package inc.nimbuspay.procustomer.service.impl;

import inc.nimbuspay.procustomer.constant.enums.CoreIdentityResponseMessage;
import inc.nimbuspay.procustomer.entity.CoreIdentity;
import inc.nimbuspay.procustomer.exception.CoreIdentityException;
import inc.nimbuspay.procustomer.repository.CoreIdentityRepository;
import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoreIdentityServiceImplTest {

    @Mock
    private DemographicDataServiceImpl demographicDataServiceImpl;

    @Mock
    private EmailAddressServiceImpl emailAddressServiceImpl;

    @Mock
    private MailAddressServiceImpl mailAddressServiceImpl;

    @Mock
    private NationalIdentityServiceImpl nationalIdentityServiceImpl;

    @Mock
    private PhoneNumberServiceImpl phoneNumberServiceImpl;

    @Mock
    private CoreIdentityRepository coreIdentityRepository;

    @InjectMocks
    private CoreIdentityServiceImpl coreIdentityService;

    @Test
    void getCoreIdentityCustomerFoundReturnsCoreIdentityResponse() {
        Long customerNumber = 12345L;
        CoreIdentity coreIdentity = new CoreIdentity();
        coreIdentity.setCustomerNumber(customerNumber);
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(coreIdentity);
        CoreIdentityResponse response = coreIdentityService.getCoreIdentity(customerNumber);
        assertEquals(customerNumber, response.getCustomerNumber());
    }

    @Test
    void getCoreIdentityCustomerNotFoundThrowsCoreIdentityException() {
        Long customerNumber = 54321L;
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.getCoreIdentity(customerNumber));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void createCoreIdentityNewCoreIdentityCreatesSuccessfully() {
        CoreIdentityRequest request = new CoreIdentityRequest();
        request.setCustomerNumber(12345L);
        when(coreIdentityRepository.existsByCustomerNumber(request.getCustomerNumber())).thenReturn(false);
        CoreIdentity savedCoreIdentity = new CoreIdentity();
        savedCoreIdentity.setCustomerNumber(request.getCustomerNumber());
        when(coreIdentityRepository.save(any(CoreIdentity.class))).thenReturn(savedCoreIdentity);
        CoreIdentityResponse response = coreIdentityService.createCoreIdentity(request);
        assertNotNull(response);
        assertEquals(request.getCustomerNumber(), response.getCustomerNumber());
        verify(coreIdentityRepository, times(1)).save(any(CoreIdentity.class));
    }

    @Test
    void createCoreIdentityExistingCoreIdentityThrowsCoreIdentityException() {
        CoreIdentityRequest request = new CoreIdentityRequest();
        request.setCustomerNumber(54321L);
        when(coreIdentityRepository.existsByCustomerNumber(request.getCustomerNumber())).thenReturn(true);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.createCoreIdentity(request));
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        verify(coreIdentityRepository, never()).save(any(CoreIdentity.class));
    }

    @Test
    void createCoreIdentityThrowsCoreIdentityExceptionOnSaveFailure() {
        CoreIdentityRequest coreIdentityRequest = new CoreIdentityRequest();
        coreIdentityRequest.setCustomerNumber(12345L);
        String workFlow = "CoreIdentityServiceImpl.createCoreIdentity";
        when(coreIdentityRepository.save(any(CoreIdentity.class)))
                .thenThrow(new CoreIdentityException("Error saving core identity", HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT, workFlow));
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.createCoreIdentity(coreIdentityRequest));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals(workFlow, exception.getWorkFlow());
        verify(coreIdentityRepository, times(1)).existsByCustomerNumber(coreIdentityRequest.getCustomerNumber());
        verify(coreIdentityRepository, times(1)).save(any(CoreIdentity.class));
    }

    @Test
    void updateCoreIdentityExistingCoreIdentityUpdatesSuccessfully() {
        Long customerNumber = 12345L;
        CoreIdentity coreIdentity = new CoreIdentity();
        coreIdentity.setCustomerNumber(customerNumber);
        CoreIdentityRequest updatedRequest = new CoreIdentityRequest();
        updatedRequest.setCustomerNumber(customerNumber);
        CoreIdentityResponse existedCoreIdentity = new CoreIdentityResponse();
        existedCoreIdentity.setConsumerId(UUID.randomUUID());
        existedCoreIdentity.setCustomerNumber(customerNumber);
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(coreIdentity);
        coreIdentityService.updateCoreIdentity(updatedRequest, customerNumber);
        verify(coreIdentityRepository, times(1)).save(any(CoreIdentity.class));
    }

    @Test
    void updateCoreIdentityNullExistingCoreIdentityThrowsCoreIdentityException() {
        Long customerNumber = 12345L;
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        CoreIdentityRequest updatedRequest = new CoreIdentityRequest();
        updatedRequest.setCustomerNumber(customerNumber);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.updateCoreIdentity(updatedRequest, customerNumber));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(String.format(CoreIdentityResponseMessage.CORE_IDENTITY_NOT_FOUND.getMessage(customerNumber)),
                exception.getMessage());
        verify(coreIdentityRepository, never()).save(any(CoreIdentity.class));
    }


    @Test
    void updateCoreIdentityNonExistingCoreIdentityThrowsCoreIdentityException() {
        Long customerNumber = 54321L;
        CoreIdentityRequest updatedRequest = new CoreIdentityRequest();
        updatedRequest.setCustomerNumber(customerNumber);
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.updateCoreIdentity(updatedRequest, customerNumber));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(coreIdentityRepository, never()).save(any(CoreIdentity.class));
    }


    @Test
    void existingCoreIdentityDeletesSuccessfully() {
        Long customerNumber = 12345L;
        CoreIdentity existedCoreIdentity = new CoreIdentity();
        existedCoreIdentity.setCustomerNumber(customerNumber);
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(existedCoreIdentity);
        coreIdentityService.deleteCoreIdentity(customerNumber);
        verify(coreIdentityRepository, times(1)).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void nonExistingCoreIdentityThrowsCoreIdentityException() {
        Long customerNumber = 54321L;
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.deleteCoreIdentity(customerNumber));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(coreIdentityRepository, never()).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void alreadyDeletedCoreIdentityThrowsCoreIdentityException() {
        Long customerNumber = 12345L;
        CoreIdentity existedCoreIdentity = new CoreIdentity();
        existedCoreIdentity.setCustomerNumber(customerNumber);
        existedCoreIdentity.setDeleted(true);
        when(coreIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(existedCoreIdentity);
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.deleteCoreIdentity(customerNumber));
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
        verify(coreIdentityRepository, never()).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void getAllCoreIdentitiesShouldReturnListOfCoreIdentityResponses() {
        CoreIdentity coreIdentity1 = new CoreIdentity();
        coreIdentity1.setConsumerId(UUID.randomUUID());
        coreIdentity1.setCustomerNumber(12345L);
        coreIdentity1.setTenantName("Tenant1");
        coreIdentity1.setFirstName("John");
        coreIdentity1.setMiddleName1("A");
        coreIdentity1.setMiddleName2("B");
        coreIdentity1.setMiddleName3("C");
        coreIdentity1.setFamilySurname("Doe");
        coreIdentity1.setPlaceOfBirth("City1");
        coreIdentity1.setMothersMaidenName("Mother1");
        coreIdentity1.setDeleted(false);
        CoreIdentity coreIdentity2 = new CoreIdentity();
        coreIdentity2.setConsumerId(UUID.randomUUID());
        coreIdentity2.setCustomerNumber(67890L);
        coreIdentity2.setTenantName("Tenant2");
        coreIdentity2.setFirstName("Jane");
        coreIdentity2.setMiddleName1("X");
        coreIdentity2.setMiddleName2(null);
        coreIdentity2.setMiddleName3("Y");
        coreIdentity2.setFamilySurname("Smith");
        coreIdentity2.setPlaceOfBirth("City2");
        coreIdentity2.setMothersMaidenName("Mother2");
        coreIdentity2.setDeleted(false);
        List<CoreIdentity> coreIdentities = Arrays.asList(coreIdentity1, coreIdentity2);
        when(coreIdentityRepository.findAll()).thenReturn(coreIdentities);
        List<CoreIdentityResponse> responses = coreIdentityService.getAllCoreIdentity();
        CoreIdentityResponse response1 = new CoreIdentityResponse();
        response1.setConsumerId(coreIdentity1.getConsumerId());
        response1.setCustomerNumber(coreIdentity1.getCustomerNumber());
        response1.setTenantName(coreIdentity1.getTenantName());
        response1.setFirstName(coreIdentity1.getFirstName());
        response1.setMiddleName1(coreIdentity1.getMiddleName1());
        response1.setMiddleName2(coreIdentity1.getMiddleName2());
        response1.setMiddleName3(coreIdentity1.getMiddleName3());
        response1.setFamilySurname(coreIdentity1.getFamilySurname());
        response1.setPlaceOfBirth(coreIdentity1.getPlaceOfBirth());
        response1.setMothersMaidenName(coreIdentity1.getMothersMaidenName());
        response1.setDeleted(coreIdentity1.isDeleted());
        CoreIdentityResponse response2 = new CoreIdentityResponse();
        response2.setConsumerId(coreIdentity2.getConsumerId());
        response2.setCustomerNumber(coreIdentity2.getCustomerNumber());
        response2.setTenantName(coreIdentity2.getTenantName());
        response2.setFirstName(coreIdentity2.getFirstName());
        response2.setMiddleName1(coreIdentity2.getMiddleName1());
        response2.setMiddleName2(coreIdentity2.getMiddleName2());
        response2.setMiddleName3(coreIdentity2.getMiddleName3());
        response2.setFamilySurname(coreIdentity2.getFamilySurname());
        response2.setPlaceOfBirth(coreIdentity2.getPlaceOfBirth());
        response2.setMothersMaidenName(coreIdentity2.getMothersMaidenName());
        response2.setDeleted(coreIdentity2.isDeleted());
        List<CoreIdentityResponse> expectedResponses = Arrays.asList(response1, response2);
        assertEquals(expectedResponses.size(), responses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            CoreIdentityResponse expected = expectedResponses.get(i);
            CoreIdentityResponse actual = responses.get(i);
            assertEquals(expected.toString(), actual.toString());
        }
        verify(coreIdentityRepository, times(1)).findAll();
    }

    @Test
    void getAllCoreIdentitiesShouldReturnEmptyListWhenNoIdentitiesFound() {
        when(coreIdentityRepository.findAll()).thenReturn(List.of());
        CoreIdentityException exception = assertThrows(CoreIdentityException.class,
                () -> coreIdentityService.getAllCoreIdentity());
        assertEquals("Core Identity not available", exception.getMessage());
        verify(coreIdentityRepository, times(1)).findAll();
    }

  

}

 