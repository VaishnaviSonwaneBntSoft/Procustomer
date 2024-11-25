package inc.nimbuspay.procustomer.constant.enums;

public enum PhoneNumberResponseMessage {
    PHONE_NUMBER_ALREADY_EXISTS("Phone number with customer number %s already exists"),
    PHONE_NUMBER_NOT_FOUND("Phone number not found for customer number: %s"),
    PHONE_NUMBER_NOT_EXISTS("Phone number not available"),
    FAILED_TO_SAVE_PHONE_NUMBER("Failed to save Phone number for customer number: %s"),
    PHONE_NUMBER_SUCCESSFULLY_DELETED("Phone number with customer number %s deleted successfully"),
    PHONE_NUMBER_SUCCESSFULLY_UPDATED("Phone number with customer number %s updated successfully"),
    PHONE_NUMBER_ALREADY_DELETED("Phone number with customer number %s already deleted");

    private final String message;

    PhoneNumberResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
