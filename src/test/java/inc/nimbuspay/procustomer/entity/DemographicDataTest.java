package inc.nimbuspay.procustomer.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

class DemographicDataTest {

    @Test
    void testAllArgsConstructor() {
        LocalDateTime timestamp = LocalDateTime.of(2024, 7, 1, 8, 0);
        UUID id = UUID.randomUUID();
        Long customerNumber = 4567890123L;
        String maritalStatus = "Separated";
        String declaredAnnualIncome = "55000";
        String occupation = "Software Developer";
        int balanceOpeningDebt = 3000;
        short numberLoans = 4;
        short numberCreditCards = 3;
        int totalExistCreditLimit = 15000;
        boolean deleted = true;
        DemographicData data = new DemographicData(id, timestamp, customerNumber, maritalStatus,
                declaredAnnualIncome, occupation, balanceOpeningDebt, numberLoans, numberCreditCards,
                totalExistCreditLimit, deleted);
        Assertions.assertThat(data.getDemographicId()).isEqualTo(id);
        Assertions.assertThat(data.getTimestamp()).isEqualTo(timestamp);
        Assertions.assertThat(data.getCustomerNumber()).isEqualTo(customerNumber);
        Assertions.assertThat(data.getMaritalStatus()).isEqualTo(maritalStatus);
        Assertions.assertThat(data.getDeclaredAnnualIncome()).isEqualTo(declaredAnnualIncome);
        Assertions.assertThat(data.getOccupation()).isEqualTo(occupation);
        Assertions.assertThat(data.getBalanceOpeningDebt()).isEqualTo(balanceOpeningDebt);
        Assertions.assertThat(data.getNumberLoans()).isEqualTo(numberLoans);
        Assertions.assertThat(data.getNumberCreditCards()).isEqualTo(numberCreditCards);
        Assertions.assertThat(data.getTotalExistCreditLimit()).isEqualTo(totalExistCreditLimit);
        Assertions.assertThat(data.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testSettersAndGetters() {
        DemographicData data = new DemographicData();
        LocalDateTime timestamp = LocalDateTime.of(2024, 7, 1, 8, 0);
        UUID id = UUID.randomUUID();
        Long customerNumber = 4567890123L;
        String maritalStatus = "Separated";
        String declaredAnnualIncome = "55000";
        String occupation = "Software Developer";
        int balanceOpeningDebt = 3000;
        short numberLoans = 4;
        short numberCreditCards = 3;
        int totalExistCreditLimit = 15000;
        boolean deleted = true;
        data.setDemographicId(id);
        data.setTimestamp(timestamp);
        data.setCustomerNumber(customerNumber);
        data.setMaritalStatus(maritalStatus);
        data.setDeclaredAnnualIncome(declaredAnnualIncome);
        data.setOccupation(occupation);
        data.setBalanceOpeningDebt(balanceOpeningDebt);
        data.setNumberLoans(numberLoans);
        data.setNumberCreditCards(numberCreditCards);
        data.setTotalExistCreditLimit(totalExistCreditLimit);
        data.setDeleted(deleted);
        Assertions.assertThat(data.getDemographicId()).isEqualTo(id);
        Assertions.assertThat(data.getTimestamp()).isEqualTo(timestamp);
        Assertions.assertThat(data.getCustomerNumber()).isEqualTo(customerNumber);
        Assertions.assertThat(data.getMaritalStatus()).isEqualTo(maritalStatus);
        Assertions.assertThat(data.getDeclaredAnnualIncome()).isEqualTo(declaredAnnualIncome);
        Assertions.assertThat(data.getOccupation()).isEqualTo(occupation);
        Assertions.assertThat(data.getBalanceOpeningDebt()).isEqualTo(balanceOpeningDebt);
        Assertions.assertThat(data.getNumberLoans()).isEqualTo(numberLoans);
        Assertions.assertThat(data.getNumberCreditCards()).isEqualTo(numberCreditCards);
        Assertions.assertThat(data.getTotalExistCreditLimit()).isEqualTo(totalExistCreditLimit);
        Assertions.assertThat(data.isDeleted()).isEqualTo(deleted);
    }

    @Test
    void testBuilder() {
        LocalDateTime timestamp = LocalDateTime.of(2024, 7, 1, 8, 0);
        UUID id = UUID.randomUUID();
        Long customerNumber = 4567890123L;
        String maritalStatus = "Separated";
        String declaredAnnualIncome = "55000";
        String occupation = "Software Developer";
        int balanceOpeningDebt = 3000;
        short numberLoans = 4;
        short numberCreditCards = 3;
        int totalExistCreditLimit = 15000;
        DemographicData data = DemographicData.builder()
                .demographicId(id)
                .timestamp(timestamp)
                .customerNumber(customerNumber)
                .maritalStatus(maritalStatus)
                .declaredAnnualIncome(declaredAnnualIncome)
                .occupation(occupation)
                .balanceOpeningDebt(balanceOpeningDebt)
                .numberLoans(numberLoans)
                .numberCreditCards(numberCreditCards)
                .totalExistCreditLimit(totalExistCreditLimit)
                .build();
        Assertions.assertThat(data.getDemographicId()).isEqualTo(id);
        Assertions.assertThat(data.getTimestamp()).isEqualTo(timestamp);
        Assertions.assertThat(data.getCustomerNumber()).isEqualTo(customerNumber);
        Assertions.assertThat(data.getMaritalStatus()).isEqualTo(maritalStatus);
        Assertions.assertThat(data.getDeclaredAnnualIncome()).isEqualTo(declaredAnnualIncome);
        Assertions.assertThat(data.getOccupation()).isEqualTo(occupation);
        Assertions.assertThat(data.getBalanceOpeningDebt()).isEqualTo(balanceOpeningDebt);
        Assertions.assertThat(data.getNumberLoans()).isEqualTo(numberLoans);
        Assertions.assertThat(data.getNumberCreditCards()).isEqualTo(numberCreditCards);
        Assertions.assertThat(data.getTotalExistCreditLimit()).isEqualTo(totalExistCreditLimit);
        Assertions.assertThat(data.isDeleted()).isFalse();
    }
}
