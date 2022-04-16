package tech.mayanksoni.cam.exceptions;

public class SendgridAPIRequestFailed extends Exception {
    public SendgridAPIRequestFailed() {
    }

    public SendgridAPIRequestFailed(String message) {
        super(message);
    }

    public SendgridAPIRequestFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public SendgridAPIRequestFailed(Throwable cause) {
        super(cause);
    }

    public SendgridAPIRequestFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
