package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_EMAIL_ADDRESS_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_EMAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.EMAIL_ADDRESS_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.EMAIL_ADDRESS_RESPONSE_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.CONTENT_TYPE;

import inc.nimbuspay.procustomer.constant.enums.EmailAddressResponseMessage;
import inc.nimbuspay.procustomer.request.EmailAddressRequest;
import inc.nimbuspay.procustomer.response.EmailAddressResponse;
import inc.nimbuspay.procustomer.service.EmailAddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailAddressController.class)
class EmailAddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmailAddressService emailAddressService;

    @Test
    void getEmailAddressShouldReturnEmailAddressResponse() throws Exception {
        Long customerNumber = 123456L;
        ClassPathResource responseResource = new ClassPathResource(EMAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        EmailAddressResponse emailAddressResponse = objectMapper.readValue(responseJson, EmailAddressResponse.class);
        emailAddressResponse.setCustomerNumber(customerNumber);
        Mockito.when(emailAddressService.getEmailAddress(customerNumber)).thenReturn(emailAddressResponse);
        String expectedJson = objectMapper.writeValueAsString(emailAddressResponse);
        MvcResult result = mockMvc.perform(get(API_EMAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(expectedJson))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        EmailAddressResponse actualEmailAddressResponse = objectMapper.readValue(actualResponseBody, EmailAddressResponse.class);
        assertEquals(emailAddressResponse.getEmailId(), actualEmailAddressResponse.getEmailId());
        assertEquals(emailAddressResponse.getCustomerNumber(), actualEmailAddressResponse.getCustomerNumber());
        assertEquals(emailAddressResponse.getEmailAddressData(), actualEmailAddressResponse.getEmailAddressData());
        assertEquals(emailAddressResponse.getEmailStatus(), actualEmailAddressResponse.getEmailStatus());
        verify(emailAddressService, times(1)).getEmailAddress(customerNumber);
    }

    @Test
    void updateEmailAddressShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 5432123L;
        ClassPathResource requestResource = new ClassPathResource(EMAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        EmailAddressRequest updateRequest = objectMapper.readValue(requestJson, EmailAddressRequest.class);
        updateRequest.setCustomerNumber(customerNumber);
        Mockito.doNothing().when(emailAddressService).updateEmailAddress(updateRequest, customerNumber);
        String updateRequestJson = objectMapper.writeValueAsString(updateRequest);
        mockMvc.perform(put(API_EMAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(CONTENT_TYPE)
                        .content(updateRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(EmailAddressResponseMessage.EMAIL_ADDRESS_SUCCESSFULLY_UPDATED.getMessage(customerNumber)));
        verify(emailAddressService, times(1)).updateEmailAddress(any(EmailAddressRequest.class), eq(customerNumber));
    }

    @Test
    void deleteEmailAddressShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 123456L;
        Mockito.doNothing().when(emailAddressService).deleteEmailAddress(customerNumber);
        MvcResult result = mockMvc.perform(delete(API_EMAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertEquals(EmailAddressResponseMessage.EMAIL_ADDRESS_SUCCESSFULLY_DELETED.getMessage(customerNumber), responseBody);
        verify(emailAddressService, times(1)).deleteEmailAddress(customerNumber);
    }

    @Test
    void createEmailAddressShouldReturnCreatedResponse() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(EMAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        EmailAddressRequest createRequest = objectMapper.readValue(requestJson, EmailAddressRequest.class);

        ClassPathResource responseResource = new ClassPathResource(EMAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        EmailAddressResponse creationResponse = objectMapper.readValue(responseJson, EmailAddressResponse.class);
        Mockito.when(emailAddressService.createEmailAddress(createRequest)).thenReturn(creationResponse);
        mockMvc.perform(post(API_EMAIL_ADDRESS_END_POINT)
                        .contentType(CONTENT_TYPE)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertEquals(createRequest.getCustomerNumber(), creationResponse.getCustomerNumber());
        assertEquals(createRequest.getEmailAddressData(), creationResponse.getEmailAddressData());
        verify(emailAddressService, times(1)).createEmailAddress(any(EmailAddressRequest.class));
    }

    @Test
    void getAllEmailAddressesShouldReturnEmailAddressList() throws Exception {
        ClassPathResource responseResource = new ClassPathResource(EMAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        EmailAddressResponse emailAddressResponse = objectMapper.readValue(responseJson, EmailAddressResponse.class);
        List<EmailAddressResponse> emailAddressResponseList = List.of(emailAddressResponse);
        Mockito.when(emailAddressService.getAllCustomerEmailAddress()).thenReturn(emailAddressResponseList);
        String expectedJson = objectMapper.writeValueAsString(emailAddressResponseList);
        MvcResult result = mockMvc.perform(get(API_EMAIL_ADDRESS_END_POINT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(expectedJson))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        List<EmailAddressResponse> actualEmailAddressResponseList = objectMapper.readValue(actualResponseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, EmailAddressResponse.class));
        assertEquals(emailAddressResponseList.size(), actualEmailAddressResponseList.size());
        assertEquals(emailAddressResponseList.getFirst().getEmailId(), actualEmailAddressResponseList.getFirst().getEmailId());
        verify(emailAddressService, times(1)).getAllCustomerEmailAddress();
    }
}