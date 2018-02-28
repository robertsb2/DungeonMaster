package Model;

public class GameLoadException extends RuntimeException {

    public GameLoadException() {
    }

    public GameLoadException(String message) {
        super(message);
    }

    public GameLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameLoadException(Throwable cause) {
        super(cause);
    }

    public GameLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
