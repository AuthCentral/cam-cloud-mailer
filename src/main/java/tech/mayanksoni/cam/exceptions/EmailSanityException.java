package tech.mayanksoni.cam.exceptions;

public class EmailSanityException extends Exception {
    private String emailDomain;

    public EmailSanityException() {
    }

    public EmailSanityException(String message) {
        super(message);
    }

    public EmailSanityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSanityException(Throwable cause) {
        super(cause);
    }

    public EmailSanityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}
