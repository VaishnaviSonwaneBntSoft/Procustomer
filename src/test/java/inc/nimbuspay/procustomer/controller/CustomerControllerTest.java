package inc.nimbuspay.procustomer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.nimbuspay.procustomer.request.CustomerSearchCriteria;
import inc.nimbuspay.procustomer.response.CustomerDetailsResponse;
import inc.nimbuspay.procustomer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA_END_POINT;
import static inc.nimbuspay.procustomer.constant.ProCustomerTestConstants.API_CUSTOMER_DETAILS_END_POINT;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCustomerDetails() throws Exception {
        CustomerDetailsResponse response = new CustomerDetailsResponse();
        when(customerService.getAllCustomerDetails(123L)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get(API_CUSTOMER_DETAILS_END_POINT, 123L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetCustomerDetailsBySearchCriteria() throws Exception {
        CustomerSearchCriteria criteria = new CustomerSearchCriteria();
        CustomerDetailsResponse response = new CustomerDetailsResponse();
        when(customerService.getCustomerDetailsBySearchCriteria(criteria)).thenReturn(Collections.singletonList(response));
        mockMvc.perform(MockMvcRequestBuilders.post(API_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA_END_POINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
