package inc.nimbuspay.procustomer.constant.enums;

public enum DemographicDataResponseMessage {
    DEMOGRAPHIC_DATA_ALREADY_EXISTS("Demographic data with customer number %s already exists"),
    DEMOGRAPHIC_DATA_NOT_FOUND("Demographic data not found for customer number: %s"),
    DEMOGRAPHIC_DATA_NOT_EXISTS("Demographic data not available"),
    FAILED_TO_SAVE_DEMOGRAPHIC_DATA("Failed to save Demographic data for customer number: %s"),
    DEMOGRAPHIC_DATA_SUCCESSFULLY_DELETED("Demographic data with customer number %s deleted successfully"),
    DEMOGRAPHIC_DATA_SUCCESSFULLY_UPDATED("Demographic data with customer number %s updated successfully"),
    DEMOGRAPHIC_DATA_ALREADY_DELETED("Demographic data with customer number %s already deleted");

    private final String message;

    DemographicDataResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Long customerNumber) {
        return String.format(message, customerNumber);
    }
}
