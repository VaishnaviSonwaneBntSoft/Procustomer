package inc.nimbuspay.procustomer.constant.enums;

public enum CustomerResponseMessage {
    CUSTOMER_DETAILS_NOT_FOUND("Customer details not found for customer number : %s"),
    CUSTOMER_DETAILS_NOT_EXITS("Customer details not available"),
    FAILED_TO_FETCH_CUSTOMER_DETAILS("Failed to fetch customer details for customer number : %s");

    private final String message;

    CustomerResponseMessage(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
