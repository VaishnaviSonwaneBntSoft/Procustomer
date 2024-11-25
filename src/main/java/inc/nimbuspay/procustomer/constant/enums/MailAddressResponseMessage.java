package inc.nimbuspay.procustomer.constant.enums;

public enum MailAddressResponseMessage {
    MAIL_ADDRESS_ALREADY_EXISTS("Mail Address with customer number %s already exists"),
    MAIL_ADDRESS_NOT_FOUND("Mail Address not found for customer number: %s"),
    MAIL_ADDRESS_NOT_EXISTS("Failed to retrieve list of mail addresses"),
    FAILED_TO_SAVE_MAIL_ADDRESS("Failed to save Mail Address"),
    MAIL_ADDRESS_SUCCESSFULLY_DELETED("Mail Address with customer number %s deleted successfully"),
    MAIL_ADDRESS_SUCCESSFULLY_UPDATED("Mail Address with customer number %s updated successfully"),
    MAIL_ADDRESS_ALREADY_DELETED("Mail Address with customer number %s already deleted");

    private final String message;

    MailAddressResponseMessage(String message) {
        this.message = message;
    }

    public String getMessageFormat() {
        return message;
    }

    public String getMessageFormat(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
