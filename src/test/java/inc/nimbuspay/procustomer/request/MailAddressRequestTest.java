package inc.nimbuspay.procustomer.request;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MailAddressRequestTest {

    private final MailAddressRequest testRequest = new MailAddressRequest();

    @BeforeEach
    void setUp() {
        testRequest.setAddressType("Residential");
        testRequest.setCustomerNumber(12345L);
        testRequest.setBuildingNumber("10A");
        testRequest.setAddressLine1("123 Main St");
        testRequest.setAddressLine2("Apt 4B");
        testRequest.setAddressLine3("Near Park");
        testRequest.setPlaceLocation("Downtown");
        testRequest.setStateCounty("California");
        testRequest.setCountry("USA");
    }

    @Test
    void testGettersAndSetters() {
        testRequest.setAddressType("UpdatedType");
        assertEquals("UpdatedType", testRequest.getAddressType());
        testRequest.setCustomerNumber(67890L);
        assertEquals(67890L, testRequest.getCustomerNumber());
    }

    @Test
    void testToString() {
        String str = testRequest.toString();
        assertTrue(str.contains("addressType=Residential"));
        assertTrue(str.contains("customerNumber=12345"));
    }


    @Test
    void testNoArgsConstructor() {
        MailAddressRequest request = new MailAddressRequest();
        assertNotNull(request);
    }


    @Test
    void testDefaultValues() {
        MailAddressRequest request = new MailAddressRequest();
        assertNull(request.getTimestamp());
        assertNull(request.getAddressType());
        assertNull(request.getCustomerNumber());
        assertNull(request.getBuildingNumber());
        assertNull(request.getAddressLine1());
        assertNull(request.getAddressLine2());
        assertNull(request.getAddressLine3());
        assertNull(request.getPlaceLocation());
        assertNull(request.getStateCounty());
        assertNull(request.getCountry());
        assertFalse(request.isDeleted());
    }

    @Test
    void testFieldAssignments() {
        assertEquals("Residential", testRequest.getAddressType());
        assertEquals(12345L, testRequest.getCustomerNumber());
        assertEquals("10A", testRequest.getBuildingNumber());
        assertEquals("123 Main St", testRequest.getAddressLine1());
        assertEquals("Apt 4B", testRequest.getAddressLine2());
        assertEquals("Near Park", testRequest.getAddressLine3());
        assertEquals("Downtown", testRequest.getPlaceLocation());
        assertEquals("California", testRequest.getStateCounty());
        assertEquals("USA", testRequest.getCountry());
        assertFalse(testRequest.isDeleted());
    }
}

