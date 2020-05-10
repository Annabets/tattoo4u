package by.bsuir.tattoo4u.controller.controllerExceptionExtn;

import by.bsuir.tattoo4u.controller.ControllerException;

public class IncorrectDataInputException extends ControllerException {

    public IncorrectDataInputException() {
        super();
    }

    public IncorrectDataInputException(String message) {
        super(message);
    }

    public IncorrectDataInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataInputException(Throwable cause) {
        super(cause);
    }
}
