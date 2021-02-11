package com.tassiano.noticeboard.noticeboard.core.dto;

public class RequestErrorValidationDto {
    String field;
    String message;

    public RequestErrorValidationDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
