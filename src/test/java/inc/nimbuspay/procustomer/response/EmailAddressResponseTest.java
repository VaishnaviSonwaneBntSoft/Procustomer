package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAddressResponseTest {

    @Test
    void testEmailIdSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        UUID emailId = UUID.randomUUID();
        response.setEmailId(emailId);
        assertThat(response.getEmailId()).isEqualTo(emailId);
    }

    @Test
    void testTimestampSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);
        assertThat(response.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testCustomerNumberSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        Long customerNumber = 12345L;
        response.setCustomerNumber(customerNumber);
        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
    }

    @Test
    void testEmailAddressDataSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        String emailAddressData = "test@example.com";
        response.setEmailAddressData(emailAddressData);
        assertThat(response.getEmailAddressData()).isEqualTo(emailAddressData);
    }

    @Test
    void testEmailStatusSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        String emailStatus = "VERIFIED";
        response.setEmailStatus(emailStatus);
        assertThat(response.getEmailStatus()).isEqualTo(emailStatus);
    }

    @Test
    void testActiveSetterAndGetter() {
        EmailAddressResponse response = new EmailAddressResponse();
        boolean active = true;
        response.setActive(active);
        assertThat(response.isActive()).isEqualTo(active);
    }

    @Test
    void testBuilder() {
        UUID emailId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 12345L;
        String emailAddress = "test@example.com";
        String emailStatus = "VERIFIED";
        boolean active = true;
        EmailAddressResponse response = EmailAddressResponse.builder()
                .emailId(emailId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .emailAddressData(emailAddress)
                .emailStatus(emailStatus)
                .isActive(active)
                .build();
        assertThat(response.getEmailId()).isEqualTo(emailId);
        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(response.getEmailAddressData()).isEqualTo(emailAddress);
        assertThat(response.getEmailStatus()).isEqualTo(emailStatus);
        assertThat(response.isActive()).isEqualTo(active);
    }

    @Test
    void testToString() {
        EmailAddressResponse response = EmailAddressResponse.builder()
                .emailId(UUID.randomUUID())
                .customerNumber(12345L)
                .emailAddressData("test@example.com")
                .emailStatus("VERIFIED")
                .isActive(true)
                .build();
        assertThat(response.toString())
                .contains("emailId", "customerNumber=12345", "emailAddressData=test@example.com", "emailStatus=VERIFIED", "isActive=true");
    }


    @Test
    void testAllArgsConstructor() {
        UUID emailId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 12345L;
        String emailAddress = "test@example.com";
        String emailStatus = "VERIFIED";
        boolean isActive = true;
        boolean deleted = false;

        EmailAddressResponse response = new EmailAddressResponse(emailId, timestamp, customerNumber, emailAddress, emailStatus, isActive, deleted);
        assertThat(response.getEmailId()).isEqualTo(emailId);
        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(response.getEmailAddressData()).isEqualTo(emailAddress);
        assertThat(response.getEmailStatus()).isEqualTo(emailStatus);
        assertThat(response.isActive()).isEqualTo(isActive);
        assertThat(response.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testNoArgsConstructor() {
        EmailAddressResponse response = new EmailAddressResponse();
        assertThat(response.getEmailId()).isNull();
        assertThat(response.getTimestamp()).isNull();
        assertThat(response.getCustomerNumber()).isNull();
        assertThat(response.getEmailAddressData()).isNull();
        assertThat(response.getEmailStatus()).isNull();
        assertThat(response.isActive()).isTrue();
    }
}

