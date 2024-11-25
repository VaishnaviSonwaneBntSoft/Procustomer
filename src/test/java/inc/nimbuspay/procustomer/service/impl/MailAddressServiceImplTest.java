package inc.nimbuspay.procustomer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.MAIL_ADDRESS_REQUEST_JSON;

import inc.nimbuspay.procustomer.constant.enums.MailAddressResponseMessage;
import inc.nimbuspay.procustomer.entity.MailAddress;
import inc.nimbuspay.procustomer.exception.MailAddressException;
import inc.nimbuspay.procustomer.mapper.MailAddressMapper;
import inc.nimbuspay.procustomer.repository.MailAddressRepository;
import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailAddressServiceImplTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private MailAddressRepository mailAddressRepository;
    @InjectMocks
    private MailAddressServiceImpl mailAddressService;

    @Test
    void createMailAddressThrowsExceptionDuringSave() {
        Long customerNumber = 12345L;
        MailAddressRequest request = new MailAddressRequest();
        request.setCustomerNumber(customerNumber);
        when(mailAddressRepository.existsByCustomerNumber(customerNumber)).thenReturn(false);
        when(mailAddressRepository.save(any(MailAddress.class)))
                .thenThrow(new MailAddressException("Simulated save exception", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR, "MailAddressService.createMailAddress"));
        MailAddressException exception = assertThrows(MailAddressException.class,
                () -> mailAddressService.createMailAddress(request));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
        assertEquals("MailAddressService.createMailAddress", exception.getWorkFlow());
        verify(mailAddressRepository, times(1)).existsByCustomerNumber(customerNumber);
        verify(mailAddressRepository, times(1)).save(any(MailAddress.class));
    }

    @Test
    void updateMailAddressShouldReturnUpdatedMailAddressResponse() throws Exception {
        Long customerNumber = 5432123L;
        UUID addressId = UUID.randomUUID();
        ClassPathResource requestResource = new ClassPathResource(MAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressRequest request = objectMapper.readValue(requestJson, MailAddressRequest.class);
        MailAddress existingMailAddress = MailAddress.builder()
                .addressId(addressId)
                .customerNumber(customerNumber)
                .addressType("Home")
                .buildingNumber("123")
                .addressLine1("Main Street")
                .addressLine2("Apt 4B")
                .addressLine3(null)
                .placeLocation("Downtown")
                .stateCounty("Los Angeles County")
                .country("USA")
                .timestamp(LocalDateTime.now())
                .deleted(false)
                .build();
        MailAddress updatedMailAddress = MailAddressMapper.INSTANCE.mailAddressRequestToMailAddress(request);
        updatedMailAddress.setAddressId(existingMailAddress.getAddressId());
        updatedMailAddress.setCustomerNumber(existingMailAddress.getCustomerNumber());
        updatedMailAddress.setDeleted(existingMailAddress.isDeleted());
        when(mailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(existingMailAddress);
        mailAddressService.updateMailAddress(request, customerNumber);
        verify(mailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(mailAddressRepository, times(1)).save(any(MailAddress.class));
    }


    @Test
    void updateMailAddressNotFoundThrowsException() {
        Long customerNumber = 12345L;
        MailAddressRequest request = new MailAddressRequest();
        request.setCustomerNumber(customerNumber);
        when(mailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        MailAddressException exception = assertThrows(MailAddressException.class,
                () -> mailAddressService.updateMailAddress(request, customerNumber));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        verify(mailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(mailAddressRepository, never()).save(any(MailAddress.class));
    }

    @Test
    void deleteMailAddressAlreadyDeletedThrowsException() {
        Long customerNumber = 12345L;
        MailAddress existingMailAddress = new MailAddress();
        existingMailAddress.setDeleted(true);
        when(mailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(existingMailAddress);
        MailAddressException exception = assertThrows(MailAddressException.class,
                () -> mailAddressService.deleteMailAddress(customerNumber));
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatusCode());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
        assertEquals("MailAddressServiceImpl.deleteMailAddress", exception.getWorkFlow());
        assertEquals(MailAddressResponseMessage.MAIL_ADDRESS_ALREADY_DELETED.getMessageFormat(customerNumber), exception.getMessage());
        verify(mailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(mailAddressRepository, never()).softDeleteByCustomerNumber(anyLong());
    }


    @Test
    void createMailAddressConflictTest() {
        MailAddressRequest request = new MailAddressRequest();
        request.setCustomerNumber(1L);
        when(mailAddressRepository.existsByCustomerNumber(1L)).thenReturn(true);
        MailAddressException exception = assertThrows(MailAddressException.class, () ->
            mailAddressService.createMailAddress(request));
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatusCode());
    }


    @Test
    void getMailAddressNotFoundTest() {
        when(mailAddressRepository.findByCustomerNumber(1L)).thenReturn(null);
        MailAddressException exception = assertThrows(MailAddressException.class, () ->
            mailAddressService.getMailAddress(1L));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
    }


    @Test
    void deleteMailAddressSuccessTest() {
        MailAddress mailAddress = new MailAddress();
        mailAddress.setCustomerNumber(1L);
        when(mailAddressRepository.findByCustomerNumber(1L)).thenReturn(mailAddress);
        doNothing().when(mailAddressRepository).softDeleteByCustomerNumber(1L);
        assertDoesNotThrow(() ->
            mailAddressService.deleteMailAddress(1L));
    }

    @Test
    void deleteMailAddressNotFoundTest() {
        when(mailAddressRepository.findByCustomerNumber(1L)).thenReturn(null);
        MailAddressException exception = assertThrows(MailAddressException.class, () ->
            mailAddressService.deleteMailAddress(1L));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
    }


    @Test
    void getAllMailAddressesEmptyTest() {
        when(mailAddressRepository.findAll()).thenReturn(List.of());
        MailAddressException exception = assertThrows(MailAddressException.class,
                () -> mailAddressService.getAllCustomerMailAddress());
        assertEquals(HttpStatus.NO_CONTENT.value(), exception.getStatusCode());
    }

    @Test
    void createMailAddressShouldReturnMailAddressResponse() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(MAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressRequest request = objectMapper.readValue(requestJson, MailAddressRequest.class);
        UUID addressId = UUID.randomUUID();
        MailAddress mailAddress = new MailAddress();
        mailAddress.setAddressId(addressId);
        mailAddress.setTimestamp(LocalDateTime.now());
        mailAddress.setAddressType(request.getAddressType());
        mailAddress.setCustomerNumber(request.getCustomerNumber());
        mailAddress.setBuildingNumber(request.getBuildingNumber());
        mailAddress.setAddressLine1(request.getAddressLine1());
        mailAddress.setAddressLine2(request.getAddressLine2());
        mailAddress.setAddressLine3(request.getAddressLine3());
        mailAddress.setPlaceLocation(request.getPlaceLocation());
        mailAddress.setStateCounty(request.getStateCounty());
        mailAddress.setCountry(request.getCountry());
        mailAddress.setDeleted(false);
        MailAddressResponse expectedResponse = new MailAddressResponse();
        expectedResponse.setAddressId(addressId);
        expectedResponse.setTimestamp(mailAddress.getTimestamp());
        expectedResponse.setAddressType(request.getAddressType());
        expectedResponse.setCustomerNumber(request.getCustomerNumber());
        expectedResponse.setBuildingNumber(request.getBuildingNumber());
        expectedResponse.setAddressLine1(request.getAddressLine1());
        expectedResponse.setAddressLine2(request.getAddressLine2());
        expectedResponse.setAddressLine3(request.getAddressLine3());
        expectedResponse.setPlaceLocation(request.getPlaceLocation());
        expectedResponse.setStateCounty(request.getStateCounty());
        expectedResponse.setCountry(request.getCountry());
        expectedResponse.setDeleted(false);
        when(mailAddressRepository.existsByCustomerNumber(request.getCustomerNumber())).thenReturn(false);
        when(mailAddressRepository.save(any(MailAddress.class))).thenReturn(mailAddress);
        MailAddressResponse result = mailAddressService.createMailAddress(request);
        assertEquals(expectedResponse, result);
        verify(mailAddressRepository, times(1)).existsByCustomerNumber(request.getCustomerNumber());
    }

    @Test
    void getMailAddressShouldReturnMailAddressResponse() {
        Long customerNumber = 5432123L;
        UUID addressId = UUID.randomUUID();
        MailAddress mailAddress = MailAddress.builder()
                .addressId(addressId)
                .customerNumber(customerNumber)
                .addressType("Home")
                .buildingNumber("123")
                .addressLine1("Main Street")
                .addressLine2("Apt 4B")
                .addressLine3(null)
                .placeLocation("Downtown")
                .stateCounty("Los Angeles County")
                .country("USA")
                .timestamp(LocalDateTime.now())
                .build();
        MailAddressResponse expectedResponse = MailAddressMapper.INSTANCE.mailAddressToMailAddressResponse(mailAddress);
        when(mailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(mailAddress);
        MailAddressResponse result = mailAddressService.getMailAddress(customerNumber);
        assertEquals(expectedResponse, result);
        verify(mailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
    }


    @Test
    void getAllMailAddressesShouldReturnListOfMailAddressResponses() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(MAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressRequest request = objectMapper.readValue(requestJson, MailAddressRequest.class);
        UUID addressId = UUID.randomUUID();
        MailAddress mailAddress = MailAddress.builder()
                .addressId(addressId)
                .customerNumber(request.getCustomerNumber())
                .addressType(request.getAddressType())
                .buildingNumber(request.getBuildingNumber())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .addressLine3(request.getAddressLine3())
                .placeLocation(request.getPlaceLocation())
                .stateCounty(request.getStateCounty())
                .country(request.getCountry())
                .timestamp(LocalDateTime.now())
                .deleted(false)
                .build();
        List<MailAddress> mailAddresses = List.of(mailAddress);
        MailAddressResponse expectedResponse = MailAddressMapper.INSTANCE.mailAddressToMailAddressResponse(mailAddress);
        when(mailAddressRepository.findAll()).thenReturn(mailAddresses);
        List<MailAddressResponse> result = mailAddressService.getAllCustomerMailAddress();
        assertEquals(1, result.size());
        MailAddressResponse resultResponse = result.getFirst();
        assertEquals(expectedResponse, resultResponse);
        verify(mailAddressRepository, times(1)).findAll();
    }
}
