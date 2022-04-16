package tech.mayanksoni.cam.exceptions;

public class RequestInvalid extends Exception {
    public RequestInvalid() {
    }

    public RequestInvalid(String message) {
        super(message);
    }

    public RequestInvalid(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestInvalid(Throwable cause) {
        super(cause);
    }

    public RequestInvalid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
