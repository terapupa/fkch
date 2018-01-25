package co.fkch.controller;

import co.fkch.exception.AttributeNotDefinedException;
import co.fkch.exception.ExceptionResponse;
import co.fkch.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Not Found");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttributeNotDefinedException.class)
    public ResponseEntity<ExceptionResponse> attributeNotDefined(AttributeNotDefinedException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("attribute Not Defined");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}