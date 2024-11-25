
package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_DEMOGRAPHIC_DATA_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_DEMOGRAPHIC_DATA_END_POINT_WITH_PATH_VARIABLE;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.DEMOGRAPHIC_DATA_REQUEST_JSON;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.DEMOGRAPHIC_DATA_RESPONSE_JSON;

import inc.nimbuspay.procustomer.constant.enums.DemographicDataResponseMessage;
import inc.nimbuspay.procustomer.request.DemographicDataRequest;
import inc.nimbuspay.procustomer.response.DemographicDataResponse;
import inc.nimbuspay.procustomer.service.DemographicDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemographicDataController.class)
class DemographicDataControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DemographicDataService demographicDataService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateDemographicDataSuccess() throws Exception {
        ClassPathResource requestResource = new ClassPathResource(DEMOGRAPHIC_DATA_REQUEST_JSON);
        String requestJson = new String(requestResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        DemographicDataRequest request = objectMapper.readValue(requestJson, DemographicDataRequest.class);
        DemographicDataResponse mockResponse = new DemographicDataResponse();
        mockResponse.setCustomerNumber(request.getCustomerNumber());
        mockResponse.setMaritalStatus(request.getMaritalStatus());
        mockResponse.setTimestamp(LocalDateTime.now());
        when(demographicDataService.createDemographicData(any(DemographicDataRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post(API_DEMOGRAPHIC_DATA_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerNumber").value(request.getCustomerNumber()))
                .andExpect(jsonPath("$.maritalStatus").value(request.getMaritalStatus()));
    }

    @Test
    void testGetAllDemographicDataSuccess() throws Exception {
        DemographicDataResponse identity1 = new DemographicDataResponse();
        DemographicDataResponse identity2 = new DemographicDataResponse();
        List<DemographicDataResponse> responseList = Arrays.asList(identity1, identity2);
        when(demographicDataService.getAllCustomerDemographicData()).thenReturn(responseList);

        mockMvc.perform(get(API_DEMOGRAPHIC_DATA_END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerNumber").value(identity1.getCustomerNumber()))
                .andExpect(jsonPath("$[1].customerNumber").value(identity2.getCustomerNumber()));
    }

    @Test
    void testGetAllDemographicDataEmpty() throws Exception {
        when(demographicDataService.getAllCustomerDemographicData()).thenReturn(List.of());

        mockMvc.perform(get(API_DEMOGRAPHIC_DATA_END_POINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetDemographicDataSuccess() throws Exception {
        Long customerNumber = 4567890123L;

        ClassPathResource responseResource = new ClassPathResource(DEMOGRAPHIC_DATA_RESPONSE_JSON);
        String responseJson = new String(responseResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        DemographicDataResponse mockResponse = objectMapper.readValue(responseJson, DemographicDataResponse.class);
        when(demographicDataService.getDemographicData(customerNumber)).thenReturn(mockResponse);

        mockMvc.perform(get(API_DEMOGRAPHIC_DATA_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.customerNumber").value(customerNumber))
                .andExpect(jsonPath("$.maritalStatus").value(mockResponse.getMaritalStatus()));
    }

    @Test
    void testDeleteDemographicDataSuccess() throws Exception {
        Long customerNumber = 4567890123L;
        doNothing().when(demographicDataService).deleteDemographicData(customerNumber);

        mockMvc.perform(delete(API_DEMOGRAPHIC_DATA_END_POINT_WITH_PATH_VARIABLE, customerNumber))
                .andExpect(status().isOk())
                .andExpect(content().string(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_DELETED.getMessage(customerNumber)));
        verify(demographicDataService, times(1)).deleteDemographicData(customerNumber);
    }

    @Test
    void testUpdateDemographicDataSuccess() throws Exception {
        Long customerNumber = 4567890123L;

        ClassPathResource requestResource = new ClassPathResource(DEMOGRAPHIC_DATA_REQUEST_JSON);
        String requestJson = new String(requestResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        doNothing().when(demographicDataService).updateDemographicData(any(DemographicDataRequest.class), eq(customerNumber));

        mockMvc.perform(put(API_DEMOGRAPHIC_DATA_END_POINT_WITH_PATH_VARIABLE, customerNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(DemographicDataResponseMessage.DEMOGRAPHIC_DATA_SUCCESSFULLY_UPDATED.getMessage(customerNumber)));
        verify(demographicDataService, times(1)).updateDemographicData(any(DemographicDataRequest.class), eq(customerNumber));
    }
}
