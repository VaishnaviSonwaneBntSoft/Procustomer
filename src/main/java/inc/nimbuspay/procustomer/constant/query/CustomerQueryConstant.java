package inc.nimbuspay.procustomer.constant.query;

public class CustomerQueryConstant {
    public static final String CUSTOMER_NUMBER = "customerNumber";
    public static final String FIRST_NAME = "firstName";
    public static final String FAMILY_SURNAME = "familySurname";
    public static final String EMAIL_ADDRESS_DATA = "emailAddressData";
    public static final String PRIMARY_PHONE_NUMBER = "primaryPhoneNumber";
    public static final String PASS_PORT_NUMBER = "passportNumber";
    public static final String DRIVING_LICENSE_NUMBER = "drivingLicenseNumber";

    public static final String GET_CUSTOMER_DETAIL_BY_CUSTOMER_NUMBER = """
            SELECT new inc.nimbuspay.procustomer.response.CustomerDetailsResponse(
                ci, ma, ea, dd, pn, ni)
            FROM CoreIdentity ci
            JOIN MailAddress ma ON ci.customerNumber = ma.customerNumber
            JOIN EmailAddress ea ON ci.customerNumber = ea.customerNumber
            JOIN DemographicData dd ON ci.customerNumber = dd.customerNumber
            JOIN PhoneNumber pn ON ci.customerNumber = pn.customerNumber
            JOIN NationalIdentity ni ON ci.customerNumber = ni.customerNumber
            WHERE ci.customerNumber = :customerNumber
            """;

    public static final String GET_CUSTOMER_DETAILS_BY_SEARCH_CRITERIA = """
            SELECT new inc.nimbuspay.procustomer.response.CustomerDetailsResponse(
                ci, ma, ea, dd, pn, ni)
            FROM CoreIdentity ci
            LEFT JOIN PhoneNumber pn ON ci.customerNumber = pn.customerNumber
            LEFT JOIN MailAddress ma ON ci.customerNumber = ma.customerNumber
            LEFT JOIN EmailAddress ea ON ci.customerNumber = ea.customerNumber
            LEFT JOIN NationalIdentity ni ON ci.customerNumber = ni.customerNumber
            LEFT JOIN DemographicData dd ON ci.customerNumber = dd.customerNumber
            WHERE ci.deleted = FALSE
                AND (
                    ci.customerNumber = :customerNumber
                    OR ci.firstName = :firstName
                    OR ci.familySurname = :familySurname
                    OR ea.emailAddressData = :emailAddressData
                    OR pn.primaryPhoneNumber = :primaryPhoneNumber
                    OR ni.passportNumber = :passportNumber
                    OR ni.drivingLicenseNumber = :drivingLicenseNumber
                )
            """;

    private CustomerQueryConstant() {
    }
}
