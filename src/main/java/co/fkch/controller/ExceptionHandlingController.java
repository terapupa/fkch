package co.fkch.controller;

import co.fkch.exception.AttributeNotDefinedException;
import co.fkch.exception.AuthException;
import co.fkch.exception.ExceptionResponse;
import co.fkch.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public GeneralResponse resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
        return new GeneralResponse<>(response);
    }

    @ExceptionHandler(AttributeNotDefinedException.class)
    public GeneralResponse attributeNotDefined(AttributeNotDefinedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("attribute Not Defined");
        response.setErrorMessage(ex.getMessage());
        return new GeneralResponse<>(response);
    }

    @ExceptionHandler(Exception.class)
    public GeneralResponse exceptionDefined(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("internal server error");
        response.setErrorMessage(ex.getMessage());
        return new GeneralResponse<>(response);
    }

    @ExceptionHandler(AuthException.class)
    public GeneralResponse exceptionDefined(AuthException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setErrorMessage(ex.getMessage());
        return new GeneralResponse<>(response);
    }

}