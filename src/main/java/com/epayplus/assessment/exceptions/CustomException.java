package com.epayplus.assessment.exceptions;

import java.util.Map;

public class CustomException extends RuntimeException{
    private int httpStatusCode;
    private String message;
    private Map<String, String> errors;

    public CustomException (int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public CustomException (int httpStatusCode, String message, Map <String, String> errors) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.errors = errors;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
