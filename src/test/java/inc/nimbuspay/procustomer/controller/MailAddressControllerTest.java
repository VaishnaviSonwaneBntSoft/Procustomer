
package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_MAIL_ADDRESS_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_MAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.MAIL_ADDRESS_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.MAIL_ADDRESS_RESPONSE_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.CONTENT_TYPE;

import inc.nimbuspay.procustomer.constant.enums.MailAddressResponseMessage;
import inc.nimbuspay.procustomer.request.MailAddressRequest;
import inc.nimbuspay.procustomer.response.MailAddressResponse;
import inc.nimbuspay.procustomer.service.MailAddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MailAddressController.class)
class MailAddressControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MailAddressService mailAddressService;

    @Test
    void createMailAddressShouldReturnCreatedResponse() throws Exception {

        ClassPathResource requestResource = new ClassPathResource(MAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        ClassPathResource responseResource = new ClassPathResource(MAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressRequest createRequest = objectMapper.readValue(requestJson, MailAddressRequest.class);
        MailAddressResponse creationResponse = objectMapper.readValue(responseJson, MailAddressResponse.class);
        Mockito.when(mailAddressService.createMailAddress(createRequest)).thenReturn(creationResponse);
        mockMvc.perform(post(API_MAIL_ADDRESS_END_POINT)
                        .contentType(CONTENT_TYPE)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        verify(mailAddressService, times(1)).createMailAddress(any(MailAddressRequest.class));
    }

    @Test
    void getMailAddressShouldReturnMailAddressResponse() throws Exception {
        Long customerNumber = 123456789L;
        ClassPathResource responseResource = new ClassPathResource(MAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressResponse mailAddressResponse = objectMapper.readValue(responseJson, MailAddressResponse.class);
        Mockito.when(mailAddressService.getMailAddress(anyLong())).thenReturn(mailAddressResponse);
        MvcResult result = mockMvc.perform(get(API_MAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().json(responseJson))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        MailAddressResponse actualResponse = objectMapper.readValue(actualResponseBody, MailAddressResponse.class);
        assertEquals(mailAddressResponse.getCustomerNumber(), actualResponse.getCustomerNumber());
        assertEquals(mailAddressResponse.getAddressId(), actualResponse.getAddressId());
        verify(mailAddressService, times(1)).getMailAddress(customerNumber);
    }

    @Test
    void updateMailAddressShouldReturnUpdatedResponse() throws Exception {
        Long customerNumber = 123456789L;
        ClassPathResource requestResource = new ClassPathResource(MAIL_ADDRESS_REQUEST_JSON);
        String requestJson = StreamUtils.copyToString(requestResource.getInputStream(), StandardCharsets.UTF_8);
        String expectedMessage = MailAddressResponseMessage.MAIL_ADDRESS_SUCCESSFULLY_UPDATED
                .getMessageFormat(customerNumber);
        Mockito.doNothing().when(mailAddressService).updateMailAddress(any(MailAddressRequest.class), eq(customerNumber));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(API_MAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(CONTENT_TYPE)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        assertEquals(expectedMessage, actualResponseBody);
        verify(mailAddressService, times(1)).updateMailAddress(any(MailAddressRequest.class), eq(customerNumber));
    }


    @Test
    void deleteMailAddressShouldReturnSuccessMessage() throws Exception {
        Long customerNumber = 123456789L;
        Mockito.doNothing().when(mailAddressService).deleteMailAddress(customerNumber);
        mockMvc.perform(delete(API_MAIL_ADDRESS_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isOk())
                .andExpect(content().string("Mail Address with customer number 123456789 deleted successfully"));
        verify(mailAddressService, times(1)).deleteMailAddress(customerNumber);
    }

    @Test
    void getAllMailAddressesShouldReturnList() throws Exception {
        ClassPathResource responseResource = new ClassPathResource(MAIL_ADDRESS_RESPONSE_JSON);
        String responseJson = StreamUtils.copyToString(responseResource.getInputStream(), StandardCharsets.UTF_8);
        MailAddressResponse response = objectMapper.readValue(responseJson, MailAddressResponse.class);
        List<MailAddressResponse> responses = Collections.singletonList(response);
        Mockito.when(mailAddressService.getAllCustomerMailAddress()).thenReturn(responses);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(API_MAIL_ADDRESS_END_POINT))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responses)))
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        List<MailAddressResponse> actualResponses = objectMapper.readValue(
                actualResponseBody,
                objectMapper.getTypeFactory().constructCollectionType(List.class, MailAddressResponse.class)
        );
        assertEquals(responses.size(), actualResponses.size());
        assertEquals(responses.getFirst(), actualResponses.getFirst());
        verify(mailAddressService, times(1)).getAllCustomerMailAddress();
    }
}
