package inc.nimbuspay.procustomer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.EMAIL_ADDRESS_REQUEST_JSON;

import inc.nimbuspay.procustomer.constant.enums.EmailAddressResponseMessage;
import inc.nimbuspay.procustomer.entity.EmailAddress;
import inc.nimbuspay.procustomer.exception.EmailAddressException;
import inc.nimbuspay.procustomer.mapper.EmailAddressMapper;
import inc.nimbuspay.procustomer.repository.EmailAddressRepository;
import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailAddressServiceImplTest {

    @Mock
    private EmailAddressRepository emailAddressRepository;

    @InjectMocks
    private EmailAddressServiceImpl emailAddressService;

    private EmailAddressRequest emailAddressRequest;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource requestResource = new ClassPathResource(EMAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        emailAddressRequest = objectMapper.readValue(requestJson, EmailAddressRequest.class);
    }

    @Test
    void testCreateEmailAddressSuccess() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        when(emailAddressRepository.existsByCustomerNumber(customerNumber)).thenReturn(false);
        EmailAddress emailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(customerNumber)
                .emailAddressData(emailAddressRequest.getEmailAddressData())
                .emailStatus(emailAddressRequest.getEmailStatus())
                .isActive(true)
                .build();
        when(emailAddressRepository.save(any(EmailAddress.class))).thenReturn(emailAddress);
        EmailAddressResponse response = emailAddressService.createEmailAddress(emailAddressRequest);
        verify(emailAddressRepository, times(1)).existsByCustomerNumber(customerNumber);
        verify(emailAddressRepository, times(1)).save(any(EmailAddress.class));
        assertNotNull(response);
        assertEquals(emailAddress.getCustomerNumber(), response.getCustomerNumber());
        assertEquals(emailAddress.getEmailAddressData(), response.getEmailAddressData());
        assertEquals(emailAddress.getEmailStatus(), response.getEmailStatus());
        assertEquals(emailAddress.isActive(), response.isActive());
    }

    @Test
    void testCreateEmailAddressAlreadyPresent() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        when(emailAddressRepository.existsByCustomerNumber(customerNumber)).thenReturn(true);
        EmailAddressException exception = assertThrows(EmailAddressException.class, () ->
                emailAddressService.createEmailAddress(emailAddressRequest));
        assertEquals("Email address with customer number " + customerNumber + " already exists", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus().value());
        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }

    @Test
    void testGetEmailAddressSuccess() {
        Long customerNumber = 123456789L;
        EmailAddress emailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(customerNumber)
                .emailAddressData("test@example.com")
                .emailStatus("ACTIVE")
                .isActive(true)
                .deleted(false)
                .build();
        EmailAddressResponse expectedResponse = EmailAddressMapper.INSTANCE.emailAddressToEmailAddressResponse(emailAddress);
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(emailAddress);
        EmailAddressResponse actualResponse = emailAddressService.getEmailAddress(customerNumber);
        verify(emailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getEmailId(), actualResponse.getEmailId());
        assertEquals(expectedResponse.getTimestamp(), actualResponse.getTimestamp());
        assertEquals(expectedResponse.getCustomerNumber(), actualResponse.getCustomerNumber());
        assertEquals(expectedResponse.getEmailAddressData(), actualResponse.getEmailAddressData());
        assertEquals(expectedResponse.getEmailStatus(), actualResponse.getEmailStatus());
        assertEquals(expectedResponse.isActive(), actualResponse.isActive());
        assertEquals(expectedResponse.isDeleted(), actualResponse.isDeleted());
    }

    @Test
    void testGetEmailAddressNotFound() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        EmailAddressException exception = assertThrows(EmailAddressException.class, () ->
                emailAddressService.getEmailAddress(customerNumber));
        assertEquals("Email address not found for customer number: " + customerNumber, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testGetAllEmailAddressSuccess() {
        EmailAddress emailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(123456789L)
                .emailAddressData("test@example.com")
                .emailStatus("ACTIVE")
                .isActive(true)
                .build();
        List<EmailAddress> emailAddresses = List.of(emailAddress);
        when(emailAddressRepository.findAll()).thenReturn(emailAddresses);
        List<EmailAddressResponse> responses = emailAddressService.getAllCustomerEmailAddress();
        verify(emailAddressRepository, times(1)).findAll();
        assertNotNull(responses);
        assertThat(responses).isNotEmpty();
    }

    @Test
    void testGetAllCustomerEmailAddressEmptyList() {
        when(emailAddressRepository.findAll()).thenReturn(new ArrayList<>());
        EmailAddressException thrownException = assertThrows(EmailAddressException.class, () ->
                emailAddressService.getAllCustomerEmailAddress());
        assertEquals(EmailAddressResponseMessage.EMAIL_ADDRESS_NOT_FOUND.getMessage(), thrownException.getMessage());
        assertEquals(HttpStatus.NO_CONTENT.value(), thrownException.getStatusCode());
        assertEquals(HttpStatus.NO_CONTENT, thrownException.getStatus());
        verify(emailAddressRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmailAddressSuccess() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        EmailAddress existingEmailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(123456789L)
                .emailAddressData("old@example.com")
                .emailStatus("INACTIVE")
                .isActive(false)
                .build();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(existingEmailAddress);
        EmailAddress updatedEmailAddress = EmailAddress.builder()
                .emailId(existingEmailAddress.getEmailId())
                .timestamp(existingEmailAddress.getTimestamp())
                .customerNumber(customerNumber)
                .emailAddressData(emailAddressRequest.getEmailAddressData())
                .emailStatus(emailAddressRequest.getEmailStatus())
                .build();
        when(emailAddressRepository.save(any(EmailAddress.class))).thenReturn(updatedEmailAddress);
        emailAddressService.updateEmailAddress(emailAddressRequest, customerNumber);
        verify(emailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
        verify(emailAddressRepository, times(1)).save(any(EmailAddress.class));
    }

    @Test
    void testUpdateEmailAddressNotFound() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        EmailAddressException exception = assertThrows(EmailAddressException.class, () ->
                emailAddressService.updateEmailAddress(emailAddressRequest, customerNumber));
        assertEquals("Email address not found for customer number: " + customerNumber, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testDeleteEmailAddressSuccess() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        EmailAddress existingEmailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(customerNumber)
                .emailAddressData("test@example.com")
                .emailStatus("ACTIVE")
                .isActive(true)
                .build();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(existingEmailAddress);
        emailAddressService.deleteEmailAddress(customerNumber);
        verify(emailAddressRepository, times(1)).findByCustomerNumber(customerNumber);
    }

    @Test
    void testDeleteEmailAddressNotFound() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        EmailAddressException exception = assertThrows(EmailAddressException.class, () ->
                emailAddressService.deleteEmailAddress(customerNumber));
        assertEquals("Email address not found for customer number: " + customerNumber, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testDeleteEmailAddressAlreadyDeleted() {
        Long customerNumber = emailAddressRequest.getCustomerNumber();
        EmailAddress existingEmailAddress = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(customerNumber)
                .emailAddressData("test@example.com")
                .emailStatus("ACTIVE")
                .isActive(true)
                .deleted(true)
                .build();
        when(emailAddressRepository.findByCustomerNumber(customerNumber)).thenReturn(existingEmailAddress);

        EmailAddressException exception = assertThrows(EmailAddressException.class, () ->
               emailAddressService.deleteEmailAddress(customerNumber));
        assertEquals("Email Address with customer number " + customerNumber + " already deleted", exception.getMessage());
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatus().value());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, exception.getStatus());
    }
}
