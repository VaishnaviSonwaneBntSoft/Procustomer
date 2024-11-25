package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_PHONE_NUMBER_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_PHONE_NUMBER_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.PHONE_NUMBER_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.CONTENT_TYPE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.PHONE_NUMBER_RESPONSE_JSON;

import inc.nimbuspay.procustomer.constant.enums.PhoneNumberResponseMessage;
import inc.nimbuspay.procustomer.request.PhoneNumberRequest;
import inc.nimbuspay.procustomer.response.PhoneNumberResponse;
import inc.nimbuspay.procustomer.service.PhoneNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhoneNumberController.class)
class PhoneNumberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PhoneNumberService phoneNumberService;

    @Test
    void createPhoneNumberShouldReturnCreatedResponse() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(PHONE_NUMBER_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        PhoneNumberRequest phoneNumberRequest = objectMapper.readValue(requestJson, PhoneNumberRequest.class);

        ClassPathResource responseResource = new ClassPathResource(PHONE_NUMBER_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        PhoneNumberResponse phoneNumberResponse = objectMapper.readValue(responseJson, PhoneNumberResponse.class);

        when(phoneNumberService.createPhoneNumber(phoneNumberRequest)).thenReturn(phoneNumberResponse);

        mockMvc.perform(post(API_PHONE_NUMBER_END_POINT)
                        .contentType(CONTENT_TYPE)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        verify(phoneNumberService, times(1)).createPhoneNumber(any(PhoneNumberRequest.class));

        assertEquals(phoneNumberRequest.getCustomerNumber(), phoneNumberResponse.getCustomerNumber());
        assertEquals(phoneNumberRequest.getPrimaryPhoneNumber(), phoneNumberResponse.getPrimaryPhoneNumber());
        assertEquals(phoneNumberRequest.getSecondaryPhoneNumber(), phoneNumberResponse.getSecondaryPhoneNumber());
        assertEquals(phoneNumberRequest.getPrimaryPhoneStatus(), phoneNumberResponse.getPrimaryPhoneStatus());

    }

    @Test
    void getAllPhoneNumbersShouldReturnPhoneNumberResponseList() throws Exception {
        ClassPathResource responseResource = new ClassPathResource(PHONE_NUMBER_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        PhoneNumberResponse phoneNumberResponse = objectMapper.readValue(responseJson, PhoneNumberResponse.class);

        List<PhoneNumberResponse> phoneNumberResponseList = List.of(phoneNumberResponse);

        when(phoneNumberService.getAllCustomerPhoneNumber()).thenReturn(phoneNumberResponseList);

        String expectedJson = objectMapper.writeValueAsString(phoneNumberResponseList);

        MvcResult result = mockMvc.perform(get(API_PHONE_NUMBER_END_POINT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(expectedJson))
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();

        List<PhoneNumberResponse> actualPhoneNumberResponseList = objectMapper.readValue(actualResponseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, PhoneNumberResponse.class));

        verify(phoneNumberService, times(1)).getAllCustomerPhoneNumber();

        assertEquals(phoneNumberResponseList.size(), actualPhoneNumberResponseList.size());
        assertEquals(phoneNumberResponseList.getFirst().getPhoneId(), actualPhoneNumberResponseList.getFirst().getPhoneId());
        assertEquals(phoneNumberResponseList.getFirst().getTimestamp(), actualPhoneNumberResponseList.getFirst().getTimestamp());
        assertEquals(phoneNumberResponseList.getFirst().getCustomerNumber(), actualPhoneNumberResponseList.getFirst().getCustomerNumber());
        assertEquals(phoneNumberResponseList.getFirst().getPrimaryPhoneNumber(), actualPhoneNumberResponseList.getFirst().getPrimaryPhoneNumber());
        assertEquals(phoneNumberResponseList.getFirst().getSecondaryPhoneNumber(), actualPhoneNumberResponseList.getFirst().getSecondaryPhoneNumber());
        assertEquals(phoneNumberResponseList.getFirst().getPrimaryPhoneStatus(), actualPhoneNumberResponseList.getFirst().getPrimaryPhoneStatus());
        assertEquals(phoneNumberResponseList.getFirst().isMobile(), actualPhoneNumberResponseList.getFirst().isMobile());

    }

    @Test
    void getPhoneNumberShouldReturnPhoneNumberResponse() throws Exception {
        Long customerNumber = 1234567890L;

        ClassPathResource responseResource = new ClassPathResource(PHONE_NUMBER_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        PhoneNumberResponse phoneNumberResponse = objectMapper.readValue(responseJson, PhoneNumberResponse.class);

        when(phoneNumberService.getPhoneNumber(customerNumber)).thenReturn(phoneNumberResponse);

        String expectedJson = objectMapper.writeValueAsString(phoneNumberResponse);

        MvcResult result = mockMvc.perform(get(API_PHONE_NUMBER_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(expectedJson))
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();

        PhoneNumberResponse actualPhoneNumberResponse = objectMapper.readValue(actualResponseBody, PhoneNumberResponse.class);

        verify(phoneNumberService, times(1)).getPhoneNumber(customerNumber);

        assertEquals(phoneNumberResponse.getPhoneId(), actualPhoneNumberResponse.getPhoneId());
        assertEquals(phoneNumberResponse.getTimestamp(), actualPhoneNumberResponse.getTimestamp());
        assertEquals(phoneNumberResponse.getCustomerNumber(), actualPhoneNumberResponse.getCustomerNumber());
        assertEquals(phoneNumberResponse.getPrimaryPhoneNumber(), actualPhoneNumberResponse.getPrimaryPhoneNumber());
        assertEquals(phoneNumberResponse.getSecondaryPhoneNumber(), actualPhoneNumberResponse.getSecondaryPhoneNumber());
        assertEquals(phoneNumberResponse.getPrimaryPhoneStatus(), actualPhoneNumberResponse.getPrimaryPhoneStatus());
        assertEquals(phoneNumberResponse.isMobile(), actualPhoneNumberResponse.isMobile());

    }

    @Test
    void updatePhoneNumberShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 1234567890L;

        ClassPathResource requestResource = new ClassPathResource(PHONE_NUMBER_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        PhoneNumberRequest updateRequest = objectMapper.readValue(requestJson, PhoneNumberRequest.class);

        doNothing().when(phoneNumberService).updatePhoneNumber(updateRequest, customerNumber);

        String updateRequestJson = objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(put(API_PHONE_NUMBER_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(CONTENT_TYPE)
                        .content(updateRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_UPDATED.getMessage(customerNumber)));

        verify(phoneNumberService, times(1)).updatePhoneNumber(any(PhoneNumberRequest.class), eq(customerNumber));
    }

    @Test
    void deletePhoneNumberShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 1234567890L;

        doNothing().when(phoneNumberService).deletePhoneNumber(customerNumber);

        MvcResult result = mockMvc.perform(delete(API_PHONE_NUMBER_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        assertEquals(PhoneNumberResponseMessage.PHONE_NUMBER_SUCCESSFULLY_DELETED.getMessage(customerNumber), responseBody);

        verify(phoneNumberService, times(1)).deletePhoneNumber(customerNumber);
    }


}