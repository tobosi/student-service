package za.co.dsignweb.studentmanager.model.exception;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException(final String message) {
        super(message);
    }
}
