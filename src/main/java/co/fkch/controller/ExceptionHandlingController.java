package co.fkch.controller;

import co.fkch.exception.AttributeNotDefinedException;
import co.fkch.exception.ExceptionResponse;
import co.fkch.exception.ResourceNotFoundException;
import co.fkch.exception.RegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new GeneralResponse<>(response), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttributeNotDefinedException.class)
    public ResponseEntity attributeNotDefined(AttributeNotDefinedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("attribute Not Defined");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new GeneralResponse<>(response), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionDefined(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("internal server error");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new GeneralResponse<>(response), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity exceptionDefined(RegisterException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(new GeneralResponse<>(response), HttpStatus.CONFLICT);
    }

}