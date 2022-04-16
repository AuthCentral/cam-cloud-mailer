package tech.mayanksoni.cam.constants;

public enum MailerServiceExceptionConstants {
    SANITY_VERIFICATION_FAILED("The domain failed sanity check"),
    DESTINATION_EMAIL_NOT_PRESENT("The destination email is not present"),
    TRANSACTION_CODE_IS_EMPTY("The request does not contain a valid transaction code"),
    SENDGRID_API_FAILED("The request failed because email api failed. Response Code : {0}"),
    MAIL_TYPE_NOT_PRESENT("The request does not contain mail type."),
    EMAIL_IDENTIFIER_IS_NOT_VALID("The email identifier is not valid"),
    ;
    public final String responseMessage;

    MailerServiceExceptionConstants(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
