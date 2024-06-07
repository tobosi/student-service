package za.co.dsignweb.studentmanager.model;

import za.co.dsignweb.studentmanager.model.exception.InvalidPhoneNumberException;
import za.co.dsignweb.studentmanager.model.exception.ValidationException;

import java.util.Objects;

public enum ValidationMessage {
    SUCCESS("Success"),
    INVALID_PHONE_NUMBER("Invalid phone number provided");

    private final String message;

    ValidationMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static ValidationException getException(final ValidationMessage validationMessage) {
        if (Objects.requireNonNull(validationMessage) == ValidationMessage.INVALID_PHONE_NUMBER) {
            return new InvalidPhoneNumberException(validationMessage.getMessage());
        }
        return new ValidationException("Unexpected error occurred");
    }
}
