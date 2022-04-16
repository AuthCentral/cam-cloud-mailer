package tech.mayanksoni.cam.constants;

public final class MailerServiceConstants {
    public static final String BLOCKLIST_SOURCE = "https://raw.githubusercontent.com/mayank1471/disposable-email-domains/master/banlist.list";
    public static final String CAM_FROM_EMAIL_ADDRESS = "mailer-cam@digitdrive.in";
    public static final String CAM_EMAIL_VERIFICATION_SUBJECT = "Please verify your email {0}";

    public static final int TRANSACTION_CODE_LEN = 80;
    public static final int VERIFICATION_CODE_LEN = 100;
    public static final int EMAIL_MONGO_ID_LEN = 80;
    public static final String VERIFICATION_EMAIL_STRING_TEMPLATE = "{0}/verify/email{1}";

    private MailerServiceConstants() {

    }
}
