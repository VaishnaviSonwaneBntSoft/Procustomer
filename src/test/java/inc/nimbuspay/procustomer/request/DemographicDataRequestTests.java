package inc.nimbuspay.procustomer.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DemographicDataRequestTests {

    @Test
    void testDefaultConstructor() {
        DemographicDataRequest request = new DemographicDataRequest();
        assertNull(request.getTimestamp());
        assertNull(request.getCustomerNumber());
        assertNull(request.getMaritalStatus());
        assertNull(request.getDeclaredAnnualIncome());
        assertNull(request.getOccupation());
        assertEquals(0, request.getBalanceOpeningDebt());
        assertEquals(0, request.getNumberLoans());
        assertEquals(0, request.getNumberCreditCards());
        assertEquals(0, request.getTotalExistCreditLimit());
        assertFalse(request.isDeleted());
    }

    @Test
    void testGettersAndSetters() {
        DemographicDataRequest request = new DemographicDataRequest();
        LocalDateTime now = LocalDateTime.now();
        Long customerNumber = 123456789L;
        String maritalStatus = "Single";
        String declaredAnnualIncome = "50000";
        String occupation = "Engineer";
        int balanceOpeningDebt = 1000;
        short numberLoans = 2;
        short numberCreditCards = 3;
        int totalExistCreditLimit = 15000;
        boolean deleted = true;
        request.setTimestamp(now);
        request.setCustomerNumber(customerNumber);
        request.setMaritalStatus(maritalStatus);
        request.setDeclaredAnnualIncome(declaredAnnualIncome);
        request.setOccupation(occupation);
        request.setBalanceOpeningDebt(balanceOpeningDebt);
        request.setNumberLoans(numberLoans);
        request.setNumberCreditCards(numberCreditCards);
        request.setTotalExistCreditLimit(totalExistCreditLimit);
        request.setDeleted(deleted);
        assertEquals(now, request.getTimestamp());
        assertEquals(customerNumber, request.getCustomerNumber());
        assertEquals(maritalStatus, request.getMaritalStatus());
        assertEquals(declaredAnnualIncome, request.getDeclaredAnnualIncome());
        assertEquals(occupation, request.getOccupation());
        assertEquals(balanceOpeningDebt, request.getBalanceOpeningDebt());
        assertEquals(numberLoans, request.getNumberLoans());
        assertEquals(numberCreditCards, request.getNumberCreditCards());
        assertEquals(totalExistCreditLimit, request.getTotalExistCreditLimit());
        assertTrue(request.isDeleted());
    }
}
