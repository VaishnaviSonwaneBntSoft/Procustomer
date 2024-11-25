package inc.nimbuspay.procustomer.constant.enums;

public enum EmailAddressResponseMessage {
    EMAIL_ADDRESS_ALREADY_EXISTS("Email address with customer number %s already exists"),
    EMAIL_ADDRESS_NOT_FOUND("Email address not found for customer number: %s"),
    EMAIL_ADDRESS_NOT_EXISTS("Email address not available"),
    FAILED_TO_SAVE_EMAIL_ADDRESS("Failed to save email address for customer number: %s"),
    EMAIL_ADDRESS_SUCCESSFULLY_DELETED("Email address with customer number %s deleted successfully"),
    EMAIL_ADDRESS_SUCCESSFULLY_UPDATED("Email address with customer number %s updated successfully"),
    EMAIL_ADDRESS_ALREADY_DELETED("Email Address with customer number %s already deleted");

    private final String message;

    EmailAddressResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }

    public String getMessage() {
        return message;
    }
}
