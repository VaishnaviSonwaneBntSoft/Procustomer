package inc.nimbuspay.procustomer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MailAddressTest {

    private MailAddress testAddress;

    @BeforeEach
    public void setup() {
        testAddress = MailAddress.builder()
                .addressId(UUID.randomUUID())
                .addressType("Residential")
                .customerNumber(12345L)
                .buildingNumber("10A")
                .addressLine1("123 Main St")
                .addressLine2("Apt 4B")
                .addressLine3("Near Park")
                .placeLocation("Downtown")
                .stateCounty("California")
                .country("USA")
                .build();
    }

    @Test
    void testBuilderPattern() {
        MailAddress address = MailAddress.builder()
                .addressType("Commercial")
                .customerNumber(67890L)
                .build();
        assertEquals("Commercial", address.getAddressType());
        assertEquals(67890L, address.getCustomerNumber());
    }

    @Test
    void testGettersAndSetters() {
        testAddress.setAddressType("UpdatedType");
        assertEquals("UpdatedType", testAddress.getAddressType());
    }

    @Test
    void testToString() {
        assertTrue(testAddress.toString().contains("addressType=Residential"));
    }


    @Test
    void testNoArgsConstructor() {
        MailAddress address = new MailAddress();
        assertNotNull(address);
    }

    @Test
    void testAllArgsConstructor() {
        MailAddress address = new MailAddress(UUID.randomUUID(), LocalDateTime.now(), "Type", 12345L, "10A", "Line1", "Line2", "Line3", "Place", "County", "Country", false);
        assertNotNull(address);
        assertEquals("Type", address.getAddressType());
    }

    @Test
    void testDeletionFlag() {
        MailAddress address = MailAddress.builder().deleted(true).build();
        assertTrue(address.isDeleted());
    }
}
