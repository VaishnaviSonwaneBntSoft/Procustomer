package inc.nimbuspay.procustomer.constant.enums;

public enum CoreIdentityResponseMessage {
    CORE_IDENTITY_ALREADY_EXISTS("Core Identity with customer number %s already exists"),
    CORE_IDENTITY_NOT_FOUND("Core Identity with customer number %s is not found"),
    CORE_IDENTITY_NOT_EXISTS("Core Identity not available"),
    FAILED_TO_SAVE_CORE_IDENTITY("Failed to save Core Identity for customer number: %s"),
    CORE_IDENTITY_SUCCESSFULLY_UPDATED("Core Identity with customer number %s updated successfully"),
    CORE_IDENTITY_SUCCESSFULLY_DELETED("Core Identity with customer number %s is deleted successfully"),
    CORE_IDENTITY_ALREADY_DELETED("Core Identity with customer number %s is already deleted");

    private final String message;

    CoreIdentityResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
