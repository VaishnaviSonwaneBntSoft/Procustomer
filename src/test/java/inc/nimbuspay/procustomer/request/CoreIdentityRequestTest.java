package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CoreIdentityRequestTest {

    @Test
    void testGetterAndSetterForTimestamp() {
        LocalDateTime expected = LocalDateTime.now();
        CoreIdentityRequest request = new CoreIdentityRequest();
        request.setTimestamp(expected);
        LocalDateTime actual = request.getTimestamp();
        assertEquals(expected, actual, "Timestamp getter and setter should work correctly");
    }

    @Test
    void testGetterAndSetterForCustomerNumber() {
        Long expected = 123456L;
        CoreIdentityRequest request = new CoreIdentityRequest();
        request.setCustomerNumber(expected);
        Long actual = request.getCustomerNumber();
        assertEquals(expected, actual, "Customer number getter and setter should work correctly");
    }

    @Test
    void testNoArgsConstructor() {
        CoreIdentityRequest request = new CoreIdentityRequest();
        assertNotNull(request, "No-args constructor should initialize the object");
    }

    @Test
    void testToString() {
        CoreIdentityRequest request = new CoreIdentityRequest();
        String expectedToString = "CoreIdentityRequest(timestamp=null, customerNumber=null, tenantName=null, " +
                "firstName=null, mothersMaidenName=null, middleName1=null, middleName2=null, middleName3=null, familySurname=null, " +
                "dateOfBirth=null, placeOfBirth=null, deleted=false)";
        String actualToString = request.toString();
        assertEquals(expectedToString, actualToString, "toString method should generate the expected string representation");
    }
}

