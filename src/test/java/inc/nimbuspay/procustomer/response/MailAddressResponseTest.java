package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MailAddressResponseTest {

    private MailAddressResponse testResponse;

    @BeforeEach
    public void setup() {
        testResponse = MailAddressResponse.builder()
                .addressId(UUID.randomUUID())
                .timestamp(LocalDateTime.now())
                .addressType("Residential")
                .customerNumber(12345L)
                .buildingNumber("10A")
                .addressLine1("123 Main St")
                .addressLine2("Apt 4B")
                .addressLine3("Near Park")
                .placeLocation("Downtown")
                .stateCounty("California")
                .country("USA")
                .deleted(false)
                .build();
    }

    @Test
    void testBuilderPattern() {
        MailAddressResponse response = MailAddressResponse.builder()
                .addressType("Commercial")
                .customerNumber(67890L)
                .build();
        assertEquals("Commercial", response.getAddressType());
        assertEquals(67890L, response.getCustomerNumber());
    }

    @Test
    void testGettersAndSetters() {
        testResponse.setAddressType("UpdatedType");
        assertEquals("UpdatedType", testResponse.getAddressType());
    }

    @Test
    void testToString() {
        assertTrue(testResponse.toString().contains("addressType=Residential"));
    }

    @Test
    void testEqualsAndHashCode() {
        MailAddressResponse response1 = MailAddressResponse.builder().addressType("Residential").build();
        MailAddressResponse response2 = MailAddressResponse.builder().addressType("Residential").build();
        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void testNoArgsConstructor() {
        MailAddressResponse response = new MailAddressResponse();
        assertNotNull(response);
    }

    @Test
    void testAllArgsConstructor() {
        MailAddressResponse response = new MailAddressResponse(UUID.randomUUID(), LocalDateTime.now(), "Type", 12345L, "10A", "Line1", "Line2", "Line3", "Place", "County", "Country", false);
        assertNotNull(response);
        assertEquals("Type", response.getAddressType());
    }

    @Test
    void testDeletionFlag() {
        MailAddressResponse response = MailAddressResponse.builder().deleted(true).build();
        assertTrue(response.isDeleted());
    }
}

