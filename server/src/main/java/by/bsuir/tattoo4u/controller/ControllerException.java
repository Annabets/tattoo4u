package by.bsuir.tattoo4u.controller;

public class ControllerException extends RuntimeException {

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause.getMessage());
    }
}
