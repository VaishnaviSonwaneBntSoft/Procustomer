package inc.nimbuspay.procustomer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.PHONE_NUMBER_REQUEST_JSON;

import inc.nimbuspay.procustomer.entity.PhoneNumber;
import inc.nimbuspay.procustomer.exception.PhoneNumberException;
import inc.nimbuspay.procustomer.repository.PhoneNumberRepository;
import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PhoneNumberServiceImplTest {

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberServiceImpl phoneNumberService;

    private PhoneNumberRequest request;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource requestResource = new ClassPathResource(PHONE_NUMBER_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        request = objectMapper.readValue(requestJson, PhoneNumberRequest.class);
    }

    @Test
    void testCreatePhoneNumberSuccess() {
        Long customerNumber = request.getCustomerNumber();
        when(phoneNumberRepository.existsByCustomerNumber(customerNumber)).thenReturn(false);

        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(1234567890L)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(false)
                .build();

        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(phoneNumber);

        PhoneNumberResponse response = phoneNumberService.createPhoneNumber(request);

        verify(phoneNumberRepository, times(1)).existsByCustomerNumber(customerNumber);
        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));

        assertNotNull(response);
        assertEquals(phoneNumber.getCustomerNumber(), response.getCustomerNumber());
        assertEquals(phoneNumber.getPrimaryPhoneNumber(), response.getPrimaryPhoneNumber());
        assertEquals(phoneNumber.getSecondaryPhoneNumber(), response.getSecondaryPhoneNumber());
        assertEquals(phoneNumber.getPrimaryPhoneStatus(), response.getPrimaryPhoneStatus());
        assertEquals(phoneNumber.isMobile(), response.isMobile());
        assertEquals(phoneNumber.isDeleted(), response.isDeleted());
    }

    @Test
    void testCreatePhoneNumberCustomerNumberExists() {
        Long customerNumber = request.getCustomerNumber();
        when(phoneNumberRepository.existsByCustomerNumber(customerNumber)).thenReturn(true);

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.createPhoneNumber(request)
        );

        verify(phoneNumberRepository, times(1)).existsByCustomerNumber(customerNumber);

        String expectedMessage = String.format("Phone number with customer number %d already exists", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus().value());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }

    @Test
    void testCreatePhoneNumberException() {
        Long customerNumber = request.getCustomerNumber();

        when(phoneNumberRepository.existsByCustomerNumber(customerNumber)).thenReturn(false);

        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenThrow(new PhoneNumberException("Failed to save phone number for customer number: " + customerNumber, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "PhoneNumberServiceImpl.createPhoneNumber"));

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.createPhoneNumber(request)
        );

        verify(phoneNumberRepository, times(1)).existsByCustomerNumber(request.getCustomerNumber());
        verify(phoneNumberRepository, times(1)).save(any(PhoneNumber.class));

        String expectedMessage = String.format("Failed to save Phone number for customer number: %d", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatus().value());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

    @Test
    void testGetAllCustomerPhoneNumberSuccess() {
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(1234567890L)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(false)
                .build();

        List<PhoneNumber> phoneNumbers = List.of(phoneNumber);

        when(phoneNumberRepository.findAll()).thenReturn(phoneNumbers);

        List<PhoneNumberResponse> responses = phoneNumberService.getAllCustomerPhoneNumber();

        verify(phoneNumberRepository, times(1)).findAll();

        assertNotNull(responses);
        assertFalse(responses.isEmpty());
    }

    @Test
    void testGetAllCustomerPhoneNumberListWhenEmpty() {
        when(phoneNumberRepository.findAll()).thenReturn(new ArrayList<>());

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.getAllCustomerPhoneNumber()
        );

        verify(phoneNumberRepository, times(1)).findAll();

        assertEquals("Phone number not available", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testGetPhoneNumberSuccess() {
        Long customerNumber = request.getCustomerNumber();

        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(1234567890L)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(false)
                .build();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.of(phoneNumber));

        PhoneNumberResponse response = phoneNumberService.getPhoneNumber(customerNumber);

        assertNotNull(response);

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);

        assertEquals(phoneNumber.getCustomerNumber(), response.getCustomerNumber());
        assertEquals(phoneNumber.getPrimaryPhoneNumber(), response.getPrimaryPhoneNumber());
        assertEquals(phoneNumber.getSecondaryPhoneNumber(), response.getSecondaryPhoneNumber());
        assertEquals(phoneNumber.getPrimaryPhoneStatus(), response.getPrimaryPhoneStatus());
        assertEquals(phoneNumber.isMobile(), response.isMobile());

    }

    @Test
    void testGetPhoneNumberNotFound() {
        Long customerNumber = request.getCustomerNumber();
        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.empty());

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.getPhoneNumber(customerNumber)
        );

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);

        String expectedMessage = String.format("Phone number not found for customer number: %d", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }


    @Test
    void testUpdatePhoneNumberSuccess() {
        Long customerNumber = request.getCustomerNumber();

        PhoneNumber existingPhoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(1234567890L)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(false)
                .build();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.of(existingPhoneNumber));

        phoneNumberService.updatePhoneNumber(request, customerNumber);

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);
    }

    @Test
    void testUpdatePhoneNumberNotFound() {
        Long customerNumber = request.getCustomerNumber();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.empty());

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.updatePhoneNumber(request, customerNumber)
        );

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);

        String expectedMessage = String.format("Phone number not found for customer number: %d", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }


    @Test
    void testDeletePhoneNumberSuccess() {
        Long customerNumber = request.getCustomerNumber();

        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(false)
                .build();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.of(phoneNumber));

        phoneNumberService.deletePhoneNumber(customerNumber);

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(phoneNumberRepository, times(1)).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void testDeletePhoneNumberNotFound() {
        Long customerNumber = request.getCustomerNumber();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.empty());

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.deletePhoneNumber(customerNumber)
        );

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);

        String expectedMessage = String.format("Phone number not found for customer number: %d", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testDeletePhoneNumberAlreadyDeleted() {
        Long customerNumber = request.getCustomerNumber();

        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .primaryPhoneNumber("8888146895")
                .secondaryPhoneNumber("9098877654")
                .primaryPhoneStatus("ACTIVE")
                .isMobile(false)
                .deleted(true)
                .build();

        when(phoneNumberRepository.findByCustomerNumber(customerNumber)).thenReturn(Optional.of(phoneNumber));

        PhoneNumberException exception = assertThrows(PhoneNumberException.class, () ->
                phoneNumberService.deletePhoneNumber(customerNumber)
        );

        verify(phoneNumberRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(phoneNumberRepository, times(0)).delete(any(PhoneNumber.class));

        String expectedMessage = String.format("Phone number with customer number %d already deleted", customerNumber);

        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatusCode());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
    }
}
