package inc.nimbuspay.procustomer.entity;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAddressTest {

    @Test
    void testEmailIdSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        UUID emailId = UUID.randomUUID();
        email.setEmailId(emailId);
        assertThat(email.getEmailId()).isEqualTo(emailId);
    }

    @Test
    void testTimestampSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        LocalDateTime timestamp = LocalDateTime.now();
        email.setTimestamp(timestamp);
        assertThat(email.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testCustomerNumberSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        Long customerNumber = 12345L;
        email.setCustomerNumber(customerNumber);
        assertThat(email.getCustomerNumber()).isEqualTo(customerNumber);
    }

    @Test
    void testEmailAddressSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        String emailAddress = "test@example.com";
        email.setEmailAddressData(emailAddress);
        assertThat(email.getEmailAddressData()).isEqualTo(emailAddress);
    }

    @Test
    void testEmailStatusSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        String emailStatus = "VERIFIED";
        email.setEmailStatus(emailStatus);
        assertThat(email.getEmailStatus()).isEqualTo(emailStatus);
    }

    @Test
    void testActiveSetterAndGetter() {
        EmailAddress email = new EmailAddress();
        boolean active = true;
        email.setActive(active);
        assertThat(email.isActive()).isEqualTo(active);
    }

    @Test
    void testBuilder() {
        UUID emailId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 12345L;
        String emailAddress = "test@example.com";
        String emailStatus = "VERIFIED";
        boolean active = true;
        EmailAddress email = EmailAddress.builder()
                .emailId(emailId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .emailAddressData(emailAddress)
                .emailStatus(emailStatus)
                .isActive(active)
                .build();
        assertThat(email.getEmailId()).isEqualTo(emailId);
        assertThat(email.getTimestamp()).isEqualTo(timestamp);
        assertThat(email.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(email.getEmailAddressData()).isEqualTo(emailAddress);
        assertThat(email.getEmailStatus()).isEqualTo(emailStatus);
        assertThat(email.isActive()).isEqualTo(active);
    }

   @Test
    void testToString() {
        EmailAddress email = EmailAddress.builder()
                .emailId(UUID.randomUUID())
                .customerNumber(12345L)
                .emailAddressData("test@example.com")
                .emailStatus("VERIFIED")
                .isActive(true)
                .build();
        assertThat(email.toString())
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
        boolean deleted = true;

        EmailAddress email = new EmailAddress(emailId, timestamp, customerNumber, emailAddress, emailStatus, isActive, deleted);

        assertThat(email.getEmailId()).isEqualTo(emailId);
        assertThat(email.getTimestamp()).isEqualTo(timestamp);
        assertThat(email.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(email.getEmailAddressData()).isEqualTo(emailAddress);
        assertThat(email.getEmailStatus()).isEqualTo(emailStatus);
        assertThat(email.isActive()).isEqualTo(isActive);
        assertThat(email.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testNoArgsConstructor() {
        EmailAddress email = new EmailAddress();
        assertThat(email.getEmailId()).isNull();
        assertThat(email.getTimestamp()).isNull();
        assertThat(email.getCustomerNumber()).isNull();
        assertThat(email.getEmailAddressData()).isNull();
        assertThat(email.getEmailStatus()).isNull();
        assertThat(email.isActive()).isTrue();
    }
}
