package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_NATIONAL_IDENTITY_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_NATIONAL_IDENTITY_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.NATIONAL_IDENTITY_RESPONSE_JSON;

import inc.nimbuspay.procustomer.constant.enums.NationalIdentityResponseMessage;
import inc.nimbuspay.procustomer.request.NationalIdentityRequest;
import inc.nimbuspay.procustomer.response.NationalIdentityResponse;
import inc.nimbuspay.procustomer.service.NationalIdentityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NationalIdentityController.class)
class NationalIdentityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NationalIdentityService nationalIdentityService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateNationalIdentitySuccess() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(NATIONAL_IDENTITY_RESPONSE_JSON);
        String requestJson = new String(requestResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
        NationalIdentityRequest request = objectMapper.readValue(requestJson, NationalIdentityRequest.class);
        NationalIdentityResponse mockResponse = new NationalIdentityResponse();
        mockResponse.setNationalId(UUID.randomUUID());
        mockResponse.setCustomerNumber(request.getCustomerNumber());
        mockResponse.setPassportNumber(request.getPassportNumber());
        mockResponse.setTimestamp(LocalDateTime.now());
        when(nationalIdentityService.createNationalIdentity(any(NationalIdentityRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post(API_NATIONAL_IDENTITY_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerNumber").value(request.getCustomerNumber()))
                .andExpect(jsonPath("$.passportNumber").value(request.getPassportNumber()));
    }

    @Test
    void testGetAllNationalIdentitiesSuccess() throws Exception {
        NationalIdentityResponse identity1 = new NationalIdentityResponse();
        NationalIdentityResponse identity2 = new NationalIdentityResponse();
        List<NationalIdentityResponse> responseList = Arrays.asList(identity1, identity2);
        Mockito.when(nationalIdentityService.getAllCustomerNationalIdentity()).thenReturn(responseList);

        mockMvc.perform(get(API_NATIONAL_IDENTITY_END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerNumber").value(identity1.getCustomerNumber()))
                .andExpect(jsonPath("$[1].passportNumber").value(identity2.getPassportNumber()));
    }

    @Test
    void testGetAllNationalIdentitiesEmpty() throws Exception {
        when(nationalIdentityService.getAllCustomerNationalIdentity()).thenReturn(List.of());

        mockMvc.perform(get(API_NATIONAL_IDENTITY_END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetNationalIdentitySuccess() throws Exception {
        Long customerNumber = 123456L;

        ClassPathResource responseResource = new ClassPathResource(NATIONAL_IDENTITY_RESPONSE_JSON);
        String responseJson = new String(responseResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
        NationalIdentityResponse mockResponse = objectMapper.readValue(responseJson,
                NationalIdentityResponse.class);
        when(nationalIdentityService.getNationalIdentity(customerNumber)).thenReturn(mockResponse);

        mockMvc.perform(get(API_NATIONAL_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.customerNumber").value(customerNumber))
                .andExpect(jsonPath("$.passportNumber").value(mockResponse.getPassportNumber()));
    }

    @Test
    void testDeleteNationalIdentitySuccessfulDeletion() throws Exception {
        Long customerNumber = 123456L;

        ClassPathResource requestResource = new ClassPathResource(NATIONAL_IDENTITY_RESPONSE_JSON);
        String requestJson = new String(requestResource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);
        NationalIdentityRequest nationalIdentityRequest = objectMapper.readValue(requestJson,
                NationalIdentityRequest.class);
        doNothing().when(nationalIdentityService).deleteNationalIdentity(customerNumber);

        mockMvc.perform(delete(API_NATIONAL_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nationalIdentityRequest)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(NationalIdentityResponseMessage.NATIONAL_IDENTITY_SUCCESSFULLY_DELETED
                                .getMessage(customerNumber)));
        verify(nationalIdentityService, times(1)).deleteNationalIdentity(customerNumber);
    }

    @Test
    void testUpdateNationalIdentitySuccess() throws Exception {
        Long customerNumber = 123456L;

        ClassPathResource requestResource = new ClassPathResource(NATIONAL_IDENTITY_RESPONSE_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        doNothing().when(nationalIdentityService).updateNationalIdentity(any(NationalIdentityRequest.class),
                eq(customerNumber));

        mockMvc.perform(put(API_NATIONAL_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "National identity with customer number 123456 updated successfully"));
        verify(nationalIdentityService, times(1)).updateNationalIdentity(any(NationalIdentityRequest.class),
                eq(customerNumber));
    }
}