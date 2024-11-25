package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAddressRequestTest {

    @Test
    void testCustomerNumberSetterAndGetter() {
        EmailAddressRequest request = new EmailAddressRequest();
        Long customerNumber = 12345L;
        request.setCustomerNumber(customerNumber);
        assertThat(request.getCustomerNumber()).isEqualTo(customerNumber);
    }

    @Test
    void testEmailAddressDataSetterAndGetter() {
        EmailAddressRequest request = new EmailAddressRequest();
        String emailAddressData = "test@example.com";
        request.setEmailAddressData(emailAddressData);
        assertThat(request.getEmailAddressData()).isEqualTo(emailAddressData);
    }

    @Test
    void testEmailStatusSetterAndGetter() {
        EmailAddressRequest request = new EmailAddressRequest();
        String emailStatus = "VERIFIED";
        request.setEmailStatus(emailStatus);
        assertThat(request.getEmailStatus()).isEqualTo(emailStatus);
    }

    @Test
    void testNoArgsConstructor() {
        EmailAddressRequest request = new EmailAddressRequest();
        assertThat(request.getCustomerNumber()).isNull();
        assertThat(request.getEmailAddressData()).isNull();
        assertThat(request.getEmailStatus()).isNull();
    }
}

