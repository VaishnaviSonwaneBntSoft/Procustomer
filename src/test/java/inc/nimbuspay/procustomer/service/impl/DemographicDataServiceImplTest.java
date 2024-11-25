package inc.nimbuspay.procustomer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.DEMOGRAPHIC_DATA_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.DEMOGRAPHIC_DATA_RESPONSE_JSON;

import inc.nimbuspay.procustomer.constant.enums.DemographicDataResponseMessage;
import inc.nimbuspay.procustomer.entity.DemographicData;
import inc.nimbuspay.procustomer.exception.DemographicDataException;
import inc.nimbuspay.procustomer.repository.DemographicDataRepository;
import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;
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
class DemographicDataServiceImplTest {

    @Mock
    private DemographicDataRepository demographicDataRepository;

    @InjectMocks
    private DemographicDataServiceImpl demographicDataService;

    private ObjectMapper objectMapper;
    private DemographicDataRequest request;

    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ClassPathResource requestResource = new ClassPathResource(DEMOGRAPHIC_DATA_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        request = objectMapper.readValue(requestJson, DemographicDataRequest.class);
    }

    @Test
    void testCreateDemographicDataSuccess() throws IOException {
        ClassPathResource responseResource = new ClassPathResource(DEMOGRAPHIC_DATA_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        DemographicDataResponse expectedResponse = objectMapper.readValue(responseJson, DemographicDataResponse.class);
        when(demographicDataRepository.existsByCustomerNumber(anyLong())).thenReturn(false);
        DemographicData demographicDataToSave = DemographicData.builder()
                .demographicId(UUID.randomUUID())
                .timestamp(request.getTimestamp())
                .customerNumber(request.getCustomerNumber())
                .maritalStatus(request.getMaritalStatus())
                .declaredAnnualIncome(request.getDeclaredAnnualIncome())
                .occupation(request.getOccupation())
                .balanceOpeningDebt(request.getBalanceOpeningDebt())
                .numberLoans(request.getNumberLoans())
                .numberCreditCards(request.getNumberCreditCards())
                .totalExistCreditLimit(request.getTotalExistCreditLimit())
                .build();
        when(demographicDataRepository.save(any(DemographicData.class))).thenReturn(demographicDataToSave);
        DemographicDataResponse response = demographicDataService.createDemographicData(request);
        verify(demographicDataRepository, times(1)).existsByCustomerNumber(anyLong());
        verify(demographicDataRepository, times(1)).save(any(DemographicData.class));
        assertNotNull(response);
        assertEquals(expectedResponse.getCustomerNumber(), response.getCustomerNumber());
        assertEquals(expectedResponse.getMaritalStatus(), response.getMaritalStatus());
        assertEquals(expectedResponse.getDeclaredAnnualIncome(), response.getDeclaredAnnualIncome());
        assertEquals(expectedResponse.getOccupation(), response.getOccupation());
        assertEquals(expectedResponse.getBalanceOpeningDebt(), response.getBalanceOpeningDebt());
        assertEquals(expectedResponse.getNumberLoans(), response.getNumberLoans());
        assertEquals(expectedResponse.getNumberCreditCards(), response.getNumberCreditCards());
        assertEquals(expectedResponse.getTotalExistCreditLimit(), response.getTotalExistCreditLimit());
    }

    @Test
    void testCreateDemographicDataCustomerNumberExists() {
        Long customerNumber = 4567890123L;
        when(demographicDataRepository.existsByCustomerNumber(customerNumber)).thenReturn(true);
        request.setCustomerNumber(customerNumber);

        DemographicDataException exception = assertThrows(DemographicDataException.class, () ->
            demographicDataService.createDemographicData(request));
        String expectedMessage = String.format("Demographic data with customer number %d already exists", customerNumber);
        assertEquals(HttpStatus.CONFLICT.value(), exception.getStatus().value());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCreateDemographicDataException() {
        when(demographicDataRepository.save(any(DemographicData.class)))
                .thenThrow(new RuntimeException("Database error"));

        DemographicDataException exception = assertThrows(DemographicDataException.class, () ->
            demographicDataService.createDemographicData(request));

        String expectedMessage = String.format("Failed to save Demographic data for customer number: %d", request.getCustomerNumber());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getStatus().value());
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testGetDemographicDataListSuccess() {
        List<DemographicData> demographicDataList = new ArrayList<>();
        demographicDataList.add(DemographicData.builder().build());
        when(demographicDataRepository.findAll()).thenReturn(demographicDataList);
        List<DemographicDataResponse> result = demographicDataService.getAllCustomerDemographicData();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(demographicDataRepository, times(1)).findAll();
    }

    @Test
    void testGetDemographicDataListWhenEmpty() {
        when(demographicDataRepository.findAll()).thenReturn(new ArrayList<>());
        DemographicDataException thrownException = assertThrows(DemographicDataException.class, () ->
                demographicDataService.getAllCustomerDemographicData());
        assertEquals(HttpStatus.NO_CONTENT.value(), thrownException.getStatusCode());
        assertEquals("Demographic data not available", thrownException.getMessage());
        verify(demographicDataRepository, times(1)).findAll();
    }

    @Test
    void testGetDemographicDataSuccess() {
        Long customerNumber = 4567890123L;
        DemographicData demographicData = DemographicData.builder()
                .demographicId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .maritalStatus("Separated")
                .declaredAnnualIncome("55000")
                .occupation("Software Developer")
                .balanceOpeningDebt(3000)
                .totalExistCreditLimit(15000)
                .timestamp(LocalDateTime.now())
                .build();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(demographicData);
        DemographicDataResponse response = demographicDataService.getDemographicData(customerNumber);
        assertNotNull(response);
        assertEquals(customerNumber, response.getCustomerNumber());
        assertEquals(demographicData.getMaritalStatus(), response.getMaritalStatus());
        assertEquals(demographicData.getDeclaredAnnualIncome(), response.getDeclaredAnnualIncome());
        assertEquals(demographicData.getOccupation(), response.getOccupation());
        assertEquals(demographicData.getBalanceOpeningDebt(), response.getBalanceOpeningDebt());
        assertEquals(demographicData.getNumberLoans(), response.getNumberLoans());
        assertEquals(demographicData.getNumberCreditCards(), response.getNumberCreditCards());
        assertEquals(demographicData.getTotalExistCreditLimit(), response.getTotalExistCreditLimit());
    }

    @Test
    void testGetDemographicDataNotFound() {
        Long customerNumber = 4567890123L;
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        DemographicDataException thrownException = assertThrows(DemographicDataException.class, () ->
                demographicDataService.getDemographicData(customerNumber));
        assertEquals(HttpStatus.NOT_FOUND.value(), thrownException.getStatus().value());
        assertEquals("Demographic data not found for customer number: " + customerNumber, thrownException.getMessage());
    }

    @Test
    void testDeleteDemographicDataSuccess() {
        Long customerNumber = request.getCustomerNumber();
        DemographicData demographicData = DemographicData.builder()
                .demographicId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .deleted(false)
                .build();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(demographicData);
        demographicDataService.deleteDemographicData(customerNumber);
        verify(demographicDataRepository, times(1)).softDeleteByCustomerNumber(customerNumber);
    }

    @Test
    void testDeleteDemographicDataNotFound() {
        Long customerNumber = request.getCustomerNumber();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        DemographicDataException exception = assertThrows(DemographicDataException.class, () ->
                demographicDataService.deleteDemographicData(customerNumber));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(customerNumber), exception.getMessage());
    }

    @Test
    void testDeleteDemographicDataAlreadyDeleted() {
        Long customerNumber = request.getCustomerNumber();
        DemographicData demographicData = DemographicData.builder()
                .demographicId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .deleted(true)
                .build();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(demographicData);
        DemographicDataException exception = assertThrows(DemographicDataException.class, () ->
                demographicDataService.deleteDemographicData(customerNumber));
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), exception.getStatus().value());
        assertEquals(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_ALREADY_DELETED.getMessage(customerNumber), exception.getMessage());
    }

    @Test
    void testUpdateDemographicDataSuccess() {
        Long customerNumber = request.getCustomerNumber();
        DemographicData existingDemographicData = DemographicData.builder()
                .demographicId(UUID.randomUUID())
                .customerNumber(customerNumber)
                .maritalStatus("Single")
                .build();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(existingDemographicData);
        demographicDataService.updateDemographicData(request, customerNumber);
        verify(demographicDataRepository, times(1)).save(any(DemographicData.class));
    }

    @Test
    void testUpdateDemographicDataNotFound() {
        Long customerNumber = request.getCustomerNumber();
        when(demographicDataRepository.findByCustomerNumber(customerNumber)).thenReturn(null);
        DemographicDataException exception = assertThrows(DemographicDataException.class, () ->
                demographicDataService.updateDemographicData(request, customerNumber));
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus().value());
        assertEquals(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_NOT_FOUND.getMessage(customerNumber), exception.getMessage());
    }
}
