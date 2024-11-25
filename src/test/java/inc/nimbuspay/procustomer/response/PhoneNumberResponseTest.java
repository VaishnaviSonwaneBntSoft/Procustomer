package inc.nimbuspay.procustomer.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberResponseTest {

    @Test
    void testNoArgsConstructor() {
        PhoneNumberResponse response = new PhoneNumberResponse();

        assertThat(response.getPhoneId()).isNull();
        assertThat(response.getTimestamp()).isNull();
        assertThat(response.getCustomerNumber()).isNull();
        assertThat(response.getPrimaryPhoneNumber()).isNull();
        assertThat(response.getSecondaryPhoneNumber()).isNull();
        assertThat(response.getPrimaryPhoneStatus()).isNull();
        assertThat(response.isMobile()).isFalse();
    }

    @Test
    void testAllArgsConstructor() {
        UUID phoneId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 1234567890L;
        String primaryPhoneNumber = "8888146895";
        String secondaryPhoneNumber = "9098877654";
        String primaryPhoneStatus = "ACTIVE";
        boolean isMobile = true;
        boolean deleted = false;

        PhoneNumberResponse response = new PhoneNumberResponse(phoneId, timestamp, customerNumber, primaryPhoneNumber, secondaryPhoneNumber, primaryPhoneStatus, isMobile, deleted);

        assertThat(response.getPhoneId()).isEqualTo(phoneId);
        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(response.getPrimaryPhoneNumber()).isEqualTo(primaryPhoneNumber);
        assertThat(response.getSecondaryPhoneNumber()).isEqualTo(secondaryPhoneNumber);
        assertThat(response.getPrimaryPhoneStatus()).isEqualTo(primaryPhoneStatus);
        assertThat(response.isMobile()).isEqualTo(isMobile);
        assertThat(response.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testBuilder() {
        UUID phoneId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Long customerNumber = 1234567890L;
        String primaryPhoneNumber = "8888146895";
        String secondaryPhoneNumber = "9098877654";
        String primaryPhoneStatus = "ACTIVE";
        boolean mobile = true;

        PhoneNumberResponse response = PhoneNumberResponse.builder()
                .phoneId(phoneId)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .primaryPhoneNumber(primaryPhoneNumber)
                .secondaryPhoneNumber(secondaryPhoneNumber)
                .primaryPhoneStatus(primaryPhoneStatus)
                .isMobile(mobile)
                .build();

        assertThat(response.getPhoneId()).isEqualTo(phoneId);
        assertThat(response.getTimestamp()).isEqualTo(timestamp);
        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
        assertThat(response.getPrimaryPhoneNumber()).isEqualTo(primaryPhoneNumber);
        assertThat(response.getSecondaryPhoneNumber()).isEqualTo(secondaryPhoneNumber);
        assertThat(response.getPrimaryPhoneStatus()).isEqualTo(primaryPhoneStatus);
        assertThat(response.isMobile()).isEqualTo(mobile);
    }

    @Test
    void testPhoneIdSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        UUID phoneId = UUID.randomUUID();

        response.setPhoneId(phoneId);

        assertThat(response.getPhoneId()).isEqualTo(phoneId);
    }

    @Test
    void testTimestampSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        LocalDateTime timestamp = LocalDateTime.now();

        response.setTimestamp(timestamp);

        assertThat(response.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testCustomerNumberSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        Long customerNumber = 1234567890L;

        response.setCustomerNumber(customerNumber);

        assertThat(response.getCustomerNumber()).isEqualTo(customerNumber);
    }

    @Test
    void testPrimaryPhoneNumberSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        String primaryPhoneNumber = "8888146895";

        response.setPrimaryPhoneNumber(primaryPhoneNumber);

        assertThat(response.getPrimaryPhoneNumber()).isEqualTo(primaryPhoneNumber);
    }

    @Test
    void testSecondaryPhoneNumberSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        String secondaryPhoneNumber = "9098877654";

        response.setSecondaryPhoneNumber(secondaryPhoneNumber);

        assertThat(response.getSecondaryPhoneNumber()).isEqualTo(secondaryPhoneNumber);
    }

    @Test
    void testPrimaryPhoneStatusSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        String primaryPhoneStatus = "ACTIVE";

        response.setPrimaryPhoneStatus(primaryPhoneStatus);

        assertThat(response.getPrimaryPhoneStatus()).isEqualTo(primaryPhoneStatus);
    }

    @Test
    void testMobileSetterAndGetter() {
        PhoneNumberResponse response = new PhoneNumberResponse();
        boolean mobile = true;

        response.setMobile(mobile);

        assertThat(response.isMobile()).isEqualTo(mobile);
    }
}
