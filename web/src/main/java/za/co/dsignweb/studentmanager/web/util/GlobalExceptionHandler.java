package za.co.dsignweb.studentmanager.web.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import za.co.dsignweb.studentmanager.api.ApiError;
import za.co.dsignweb.studentmanager.model.exception.NotFoundException;
import za.co.dsignweb.studentmanager.model.exception.ValidationException;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(final MethodArgumentNotValidException e, final WebRequest webRequest) throws URISyntaxException {
        final List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.add(String.format("%s %s", fieldName, errorMessage));
        });
        return getApiError(e, HttpStatus.BAD_REQUEST, webRequest, errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(final ConstraintViolationException e, final WebRequest webRequest) throws URISyntaxException {
        final List<String> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(error -> {
            errors.add(String.format("%s %s", error.getPropertyPath(), error.getMessage()));
        });
        return getApiError(e, HttpStatus.BAD_REQUEST, webRequest, errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(final ValidationException e, final WebRequest webRequest) throws URISyntaxException {
        return getApiError(e, HttpStatus.BAD_REQUEST, webRequest);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> onResourceNotFoundException(final NotFoundException e, final WebRequest webRequest) throws URISyntaxException {
        return getApiError(e, HttpStatus.NOT_FOUND, webRequest);
    }

    private ResponseEntity<ApiError> getApiError(final Exception e, final HttpStatus httpStatus, final WebRequest webRequest) throws URISyntaxException {
        return new ResponseEntity<>(new ApiError(httpStatus, e, e.getMessage(),  webRequest), httpStatus);
    }

    private ResponseEntity<ApiError> getApiError(final Exception e, final HttpStatus httpStatus, final WebRequest webRequest, final List<String> errors) throws URISyntaxException {
        return new ResponseEntity<>(new ApiError(httpStatus, e, errors.toString(),  webRequest), httpStatus);
    }
}
