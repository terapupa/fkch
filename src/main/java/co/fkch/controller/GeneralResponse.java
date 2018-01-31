package co.fkch.controller;

import co.fkch.exception.ExceptionResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class GeneralResponse<T> extends ResourceSupport {
    private T body;
    private ExceptionResponse error;

    public GeneralResponse() {
    }

    @JsonCreator
    public GeneralResponse(@JsonProperty("body") T body) {
        this.body = body;
    }

    @JsonCreator
    public GeneralResponse(@JsonProperty("error") ExceptionResponse error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public ExceptionResponse getError() {
        return error;
    }

    public void setError(ExceptionResponse error) {
        this.error = error;
    }
}
