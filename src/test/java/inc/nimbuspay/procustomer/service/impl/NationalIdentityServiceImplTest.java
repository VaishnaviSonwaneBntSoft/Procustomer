package inc.nimbuspay.procustomer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.NATIONAL_IDENTITY_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.NATIONAL_IDENTITY_RESPONSE_JSON;

import inc.nimbuspay.procustomer.constant.enums.NationalIdentityResponseMessage;
import inc.nimbuspay.procustomer.entity.NationalIdentity;
import inc.nimbuspay.procustomer.exception.NationalIdentityException;
import inc.nimbuspay.procustomer.mapper.NationalIdentityMapper;
import inc.nimbuspay.procustomer.repository.NationalIdentityRepository;
import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NationalIdentityServiceImplTest {

    @Mock
    private NationalIdentityRepository nationalIdentityRepository;

    @InjectMocks
    private NationalIdentityServiceImpl nationalIdentityService;

    private ObjectMapper objectMapper;
    private NationalIdentityRequest request;

    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        ClassPathResource requestResource = new ClassPathResource(NATIONAL_IDENTITY_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        request = objectMapper.readValue(requestJson, NationalIdentityRequest.class);
    }

    @Test
    void testCreateNationalIdentitySuccess() throws IOException {

        ClassPathResource responseResource = new ClassPathResource(NATIONAL_IDENTITY_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        NationalIdentityResponse expectedResponse = objectMapper.readValue(responseJson,
                NationalIdentityResponse.class);
        when(nationalIdentityRepository.existsByCustomerNumber(anyLong())).thenReturn(false);
        NationalIdentity nationalIdentityToSave = NationalIdentity.builder()
                .nationalId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(request.getCustomerNumber())
                .passportNumber(request.getPassportNumber())
                .drivingLicenseNumber(request.getDrivingLicenseNumber())
                .nationalTaxId(request.getNationalTaxId())
                .build();
        when(nationalIdentityRepository.save(any(NationalIdentity.class))).thenReturn(nationalIdentityToSave);
        NationalIdentityResponse response = nationalIdentityService.createNationalIdentity(request);
        verify(nationalIdentityRepository, times(1)).existsByCustomerNumber(anyLong());
        verify(nationalIdentityRepository, times(1)).save(any(NationalIdentity.class));
        assertNotNull(response);
        assertEquals(expectedResponse.getCustomerNumber(), response.getCustomerNumber());
        assertEquals(expectedResponse.getPassportNumber(), response.getPassportNumber());
        assertEquals(expectedResponse.getDrivingLicenseNumber(), response.getDrivingLicenseNumber());
        assertEquals(expectedResponse.getNationalTaxId(), response.getNationalTaxId());
    }

    @Test
    void testCreateNationalIdentityCustomerNumberExists() {
        when(nationalIdentityRepository.existsByCustomerNumber(anyLong())).thenReturn(true);

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.createNationalIdentity(request));

        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus().value());
        assertEquals(NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_EXISTS.getMessageFormat(),
                exception.getMessage());
    }

    //@Test
    void testCreateNationalIdentityException() {
        when(nationalIdentityRepository.save(any(NationalIdentity.class)))
                .thenThrow(new RuntimeException("Database error"));

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.createNationalIdentity(request));
        String expectedMessage = String.format("Failed to save national identity for customer number: %d", request.getCustomerNumber());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatus().value());
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void testGetNationalIdentitiesSuccess() {
        List<NationalIdentity> nationalIdentities = new ArrayList<>();
        nationalIdentities.add(new NationalIdentity());
        when(nationalIdentityRepository.findAll()).thenReturn(nationalIdentities);
        List<NationalIdentityResponse> result = nationalIdentityService.getAllCustomerNationalIdentity();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(nationalIdentityRepository, times(1)).findAll();
    }

    @Test
    void testGetNationalIdentitiesWhenEmpty() {
        when(nationalIdentityRepository.findAll()).thenReturn(new ArrayList<>());
        NationalIdentityException thrownException = assertThrows(NationalIdentityException.class,
                () -> nationalIdentityService.getAllCustomerNationalIdentity());
        assertEquals(NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_EXISTS.getMessageFormat(), thrownException.getMessage());
        assertEquals(HttpStatus.NO_CONTENT.value(), thrownException.getStatusCode());
        assertEquals(HttpStatus.NO_CONTENT, thrownException.getStatus());
        verify(nationalIdentityRepository, times(1)).findAll();
    }

    @Test
    void testGetNationalIdentitySuccess() {
        Long customerNumber = 12345L;
        NationalIdentity nationalIdentity = NationalIdentity.builder()
                .nationalId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .passportNumber("PASS123")
                .drivingLicenseNumber("DL123")
                .nationalTaxId("TAXIED123")
                .timestamp(LocalDateTime.now())
                .build();
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(nationalIdentity);
        NationalIdentityResponse response = nationalIdentityService.getNationalIdentity(customerNumber);
        assertNotNull(response);
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(nationalIdentity.getPassportNumber(), response.getPassportNumber());
        assertEquals(nationalIdentity.getDrivingLicenseNumber(), response.getDrivingLicenseNumber());
        assertEquals(nationalIdentity.getNationalTaxId(), response.getNationalTaxId());
    }

    @Test
    void testGetNationalIdentityNotFound() {
        Long customerNumber = 99999L;
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.getNationalIdentity(customerNumber));

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                exception.getMessage());
    }

    @Test
    void testDeleteNationalIdentitySuccess() {
        Long customerNumber = 123456L;
        NationalIdentity nationalIdentity = NationalIdentity.builder()
                .nationalId(UUID.randomUUID())
                .customerNumber(request.getCustomerNumber())
                .passportNumber(request.getPassportNumber())
                .drivingLicenseNumber(request.getDrivingLicenseNumber())
                .nationalTaxId(request.getNationalTaxId())
                .timestamp(LocalDateTime.now())
                .build();
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(nationalIdentity);
        nationalIdentityService.deleteNationalIdentity(customerNumber);
        verify(nationalIdentityRepository, times(1)).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void testDeleteNationalIdentityNotFound() {
        Long customerNumber = 99999L;
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.deleteNationalIdentity(customerNumber));

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(NationalIdentityResponseMessage.NATIONAL_IDENTITY_NOT_FOUND.getMessage(customerNumber),
                exception.getMessage());
    }

    @Test
    void testDeleteNationalIdentityAlreadyDeleted() {
        Long customerNumber = 12345L;
        NationalIdentity nationalIdentity = NationalIdentity.builder()
                .customerNumber(customerNumber)
                .deleted(true)
                .build();
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(nationalIdentity);

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.deleteNationalIdentity(customerNumber));

        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatusCode());
        assertEquals(NationalIdentityResponseMessage.NATIONAL_IDENTITY_ALREADY_DELETED.getMessage(customerNumber),
                exception.getMessage());
    }

    @Test
    void testUpdateNationalIdentitySuccess() {
        when(nationalIdentityRepository.findByCustomerNumber(anyLong()))
                .thenReturn(NationalIdentityMapper.INSTANCE.nationalIdentityRequestToNationalIdentity(request));
        nationalIdentityService.updateNationalIdentity(request, request.getCustomerNumber());
        verify(nationalIdentityRepository, times(1)).findByCustomerNumber(anyLong());
        verify(nationalIdentityRepository, times(1)).save(any(NationalIdentity.class));
    }

    @Test
    void testUpdateNationalIdentityNotFound() throws IOException {
        ClassPathResource requestResource = new ClassPathResource(NATIONAL_IDENTITY_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        NationalIdentityRequest updatedNationalIdentity = objectMapper.readValue(requestJson,
                NationalIdentityRequest.class);
        Long customerNumber = updatedNationalIdentity.getCustomerNumber();
        when(nationalIdentityRepository.findByCustomerNumber(customerNumber)).thenReturn(null);

        NationalIdentityException exception = assertThrows(NationalIdentityException.class, () ->
            nationalIdentityService.updateNationalIdentity(updatedNationalIdentity, customerNumber));

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode());
        verify(nationalIdentityRepository).findByCustomerNumber(customerNumber);
        verifyNoMoreInteractions(nationalIdentityRepository);
    }
}
