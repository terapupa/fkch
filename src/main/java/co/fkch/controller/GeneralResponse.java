package co.fkch.controller;

import co.fkch.exception.ExceptionResponse;

public class GeneralResponse<T> {
    private T body;
    private ExceptionResponse error;

    public GeneralResponse() {
    }

    public GeneralResponse(T body) {
        this.body = body;
    }

    public GeneralResponse(ExceptionResponse error) {
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
