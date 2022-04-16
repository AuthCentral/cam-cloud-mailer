package tech.mayanksoni.cam.exceptions;

public class MailerServiceException extends Exception{
    public MailerServiceException() {
    }

    public MailerServiceException(String message) {
        super(message);
    }

    public MailerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailerServiceException(Throwable cause) {
        super(cause);
    }

    public MailerServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
