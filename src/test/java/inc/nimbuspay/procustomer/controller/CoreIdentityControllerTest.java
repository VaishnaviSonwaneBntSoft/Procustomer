package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_CORE_IDENTITY_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_CORE_IDENTITY_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.CORE_IDENTITY_RESPONSE_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.CONTENT_TYPE;

import inc.nimbuspay.procustomer.constant.enums.CoreIdentityResponseMessage;
import inc.nimbuspay.procustomer.request.CoreIdentityRequest;
import inc.nimbuspay.procustomer.response.CoreIdentityResponse;
import inc.nimbuspay.procustomer.service.CoreIdentityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CoreIdentityController.class)
class CoreIdentityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CoreIdentityService coreIdentityService;

    @Test
    void getCoreIdentityShouldReturnCoreIdentityResponse() throws Exception {
        Long customerNumber = 123456L;
        UUID consumerId = UUID.randomUUID();
        ClassPathResource requestResource = new ClassPathResource(CORE_IDENTITY_RESPONSE_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        CoreIdentityResponse coreIdentityResponse = objectMapper.readValue(requestJson, CoreIdentityResponse.class);
        coreIdentityResponse.setConsumerId(consumerId);
        coreIdentityResponse.setCustomerNumber(customerNumber);
        Mockito.when(coreIdentityService.getCoreIdentity(customerNumber)).thenReturn(coreIdentityResponse);
        String expectedJson = objectMapper.writeValueAsString(coreIdentityResponse);
        MvcResult result = mockMvc.perform(get(API_CORE_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(expectedJson))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        CoreIdentityResponse actualCoreIdentityResponse = objectMapper.readValue(actualResponseBody, CoreIdentityResponse.class);
        assertEquals(coreIdentityResponse.getConsumerId(), actualCoreIdentityResponse.getConsumerId());
        assertEquals(coreIdentityResponse.getCustomerNumber(), actualCoreIdentityResponse.getCustomerNumber());
        assertEquals(coreIdentityResponse.getTimestamp(), actualCoreIdentityResponse.getTimestamp());
        verify(coreIdentityService, times(1)).getCoreIdentity(customerNumber);

    }

    @Test
    void updateCoreIdentityShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 5432123L;
        ClassPathResource requestResource = new ClassPathResource(CORE_IDENTITY_RESPONSE_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        CoreIdentityRequest updateRequest = objectMapper.readValue(requestJson, CoreIdentityRequest.class);
        updateRequest.setCustomerNumber(customerNumber);
        String updateRequestJson = objectMapper.writeValueAsString(updateRequest);
        Mockito.doNothing().when(coreIdentityService).updateCoreIdentity(updateRequest, customerNumber);
        mockMvc.perform(MockMvcRequestBuilders.put(API_CORE_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(CONTENT_TYPE)
                        .content(updateRequestJson))
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_UPDATED.getMessage(customerNumber)));

        verify(coreIdentityService, times(1)).updateCoreIdentity(any(CoreIdentityRequest.class), eq(customerNumber));
    }

    @Test
    void deleteCoreIdentityShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 123456L;
        Mockito.doNothing().when(coreIdentityService).deleteCoreIdentity(customerNumber);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(API_CORE_IDENTITY_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertEquals(CoreIdentityResponseMessage.CORE_IDENTITY_SUCCESSFULLY_DELETED.getMessage(customerNumber), responseBody);
        Mockito.verify(coreIdentityService, Mockito.times(1)).deleteCoreIdentity(customerNumber);
    }

    @Test
    void createCoreIdentityShouldReturnCreatedResponse() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(CORE_IDENTITY_RESPONSE_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        String responseJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        CoreIdentityRequest createRequest = objectMapper.readValue(requestJson, CoreIdentityRequest.class);
        CoreIdentityResponse creationResponse = objectMapper.readValue(responseJson, CoreIdentityResponse.class);
        Mockito.when(coreIdentityService.createCoreIdentity(createRequest)).thenReturn(creationResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(API_CORE_IDENTITY_END_POINT)
                        .contentType(CONTENT_TYPE)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals(createRequest.getCustomerNumber(), creationResponse.getCustomerNumber());
        assertEquals(createRequest.getTimestamp(), creationResponse.getTimestamp());
        verify(coreIdentityService, Mockito.times(1)).createCoreIdentity(any(CoreIdentityRequest.class));
    }

    @Test
    void getAllCoreIdentitiesShouldReturnListOfCoreIdentityResponses() throws Exception {
        ClassPathResource responseResource = new ClassPathResource(CORE_IDENTITY_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        CoreIdentityResponse coreIdentityResponse = objectMapper.readValue(responseJson, CoreIdentityResponse.class);
        List<CoreIdentityResponse> coreIdentityResponses = Arrays.asList(coreIdentityResponse, coreIdentityResponse);
        Mockito.when(coreIdentityService.getAllCoreIdentity()).thenReturn(coreIdentityResponses);
        MvcResult result = mockMvc.perform(get(API_CORE_IDENTITY_END_POINT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        List<CoreIdentityResponse> actualCoreIdentityResponses = objectMapper.readValue(
                actualResponseBody,
                new TypeReference<>() {
                });
        assertEquals(coreIdentityResponses.size(), actualCoreIdentityResponses.size());
        for (int i = 0; i < coreIdentityResponses.size(); i++) {
            assertEquals(coreIdentityResponses.get(i).toString(), actualCoreIdentityResponses.get(i).toString());
        }
        verify(coreIdentityService, times(1)).getAllCoreIdentity();
    }

   

}
