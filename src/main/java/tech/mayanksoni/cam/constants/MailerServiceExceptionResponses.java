package tech.mayanksoni.cam.constants;

public final class MailerServiceExceptionResponses {
    public static final String SANITY_RESPONSE_ERROR = "The email domain is banned from the service, email domain identified {0}";
    public static final String REQUEST_IS_INVALID = "The request does not contain all required information, {0}";
    public static final String MAILING_DOWNSTREAM_API_FAILED = "The server was unable to complete the request due to a service exception with the email provider";
}
