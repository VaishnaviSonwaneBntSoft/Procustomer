package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberRequestTest {

    @Test
    void testNoArgsConstructor() {
        PhoneNumberRequest request = new PhoneNumberRequest();

        assertThat(request.getCustomerNumber()).isNull();
        assertThat(request.getPrimaryPhoneNumber()).isNull();
        assertThat(request.getSecondaryPhoneNumber()).isNull();
        assertThat(request.getPrimaryPhoneStatus()).isNull();
    }

    @Test
    void testCustomerNumberSetterAndGetter() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        Long customerNumber = 1234567890L;

        request.setCustomerNumber(customerNumber);

        assertThat(request.getCustomerNumber()).isEqualTo(customerNumber);
    }

    @Test
    void testPrimaryPhoneNumberSetterAndGetter() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        String defaultPhoneNumber = "8888146895";

        request.setPrimaryPhoneNumber(defaultPhoneNumber);

        assertThat(request.getPrimaryPhoneNumber()).isEqualTo(defaultPhoneNumber);
    }

    @Test
    void testSecondaryPhoneNumberSetterAndGetter() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        String phoneNumberData = "9098877654";

        request.setSecondaryPhoneNumber(phoneNumberData);

        assertThat(request.getSecondaryPhoneNumber()).isEqualTo(phoneNumberData);
    }

    @Test
    void testPrimaryPhoneStatusSetterAndGetter() {
        PhoneNumberRequest request = new PhoneNumberRequest();
        String phoneStatus = "ACTIVE";

        request.setPrimaryPhoneStatus(phoneStatus);

        assertThat(request.getPrimaryPhoneStatus()).isEqualTo(phoneStatus);
    }
}
