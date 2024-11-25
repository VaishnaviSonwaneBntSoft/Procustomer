package inc.nimbuspay.procustomer.constant.enums;

public enum NationalIdentityResponseMessage {
    NATIONAL_IDENTITY_ALREADY_EXISTS("National identity with customer number %s already exists"),
    NATIONAL_IDENTITY_NOT_FOUND("National identity not found for customer number: %s"),
    NATIONAL_IDENTITY_NOT_EXISTS("National identity not available"),
    FAILED_TO_SAVE_NATIONAL_IDENTITY("Failed to save national identity for customer number: %s"),
    NATIONAL_IDENTITY_SUCCESSFULLY_DELETED("National identity with customer number %s deleted successfully"),
    NATIONAL_IDENTITY_SUCCESSFULLY_UPDATED("National identity with customer number %s updated successfully"),
    NATIONAL_IDENTITY_ALREADY_DELETED("National identity with customer number %s already deleted");

    private final String message;

    NationalIdentityResponseMessage(String message) {
        this.message = message;
    }

    public String getMessageFormat() {
        return message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
