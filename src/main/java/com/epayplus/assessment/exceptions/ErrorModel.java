package com.epayplus.assessment.exceptions;

import java.util.Map;


public class ErrorModel {

    private final String status = "failure";
    private final String code = "99";
    private String message;
    private Map<String, String> errors;

    public ErrorModel(String message,  Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
