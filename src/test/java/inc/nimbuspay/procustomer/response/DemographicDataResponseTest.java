package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DemographicDataResponseTest {

    private DemographicDataResponse testResponse;

    @BeforeEach
    public void setup() {
        testResponse = DemographicDataResponse.builder()
                .demographicId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .customerNumber(1234567890L)
                .maritalStatus("Married")
                .declaredAnnualIncome("60000")
                .occupation("Engineer")
                .balanceOpeningDebt(5000)
                .numberLoans((short) 2)
                .numberCreditCards((short) 3)
                .totalExistCreditLimit(20000)
                .deleted(false)
                .build();
    }

    @Test
    void testBuilderPattern() {
        DemographicDataResponse response = DemographicDataResponse.builder()
                .maritalStatus("Single")
                .customerNumber(9876543210L)
                .build();
        assertEquals("Single", response.getMaritalStatus());
        assertEquals(9876543210L, response.getCustomerNumber());
    }

    @Test
    void testGettersAndSetters() {
        testResponse.setMaritalStatus("Divorced");
        assertEquals("Divorced", testResponse.getMaritalStatus());
    }

    @Test
    void testToString() {
        assertTrue(testResponse.toString().contains("maritalStatus=Married"));
    }

    @Test
    void testNoArgsConstructor() {
        DemographicDataResponse response = new DemographicDataResponse();
        assertNotNull(response);
    }

    @Test
    void testAllArgsConstructor() {
        DemographicDataResponse response = new DemographicDataResponse(
                UUID.randomUUID(), LocalDateTime.now(), 1234567890L, "Widowed", "50000", "Teacher",
                1000, (short) 1, (short) 2, 15000, true);
        assertNotNull(response);
        assertEquals("Widowed", response.getMaritalStatus());
        assertTrue(response.isDeleted());
    }

    @Test
    void testDeletionFlag() {
        DemographicDataResponse response = DemographicDataResponse.builder().deleted(true).build();
        assertTrue(response.isDeleted());
    }
}

