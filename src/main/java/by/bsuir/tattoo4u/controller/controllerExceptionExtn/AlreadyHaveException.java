package by.bsuir.tattoo4u.controller.controllerExceptionExtn;

import by.bsuir.tattoo4u.controller.ControllerException;

public class AlreadyHaveException extends ControllerException {

    public AlreadyHaveException() {
        super();
    }

    public AlreadyHaveException(String message) {
        super(message);
    }

    public AlreadyHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyHaveException(Throwable cause) {
        super(cause);
    }
}
