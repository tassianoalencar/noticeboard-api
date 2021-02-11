package com.tassiano.noticeboard.noticeboard.core.handlers;

import com.tassiano.noticeboard.noticeboard.core.dto.RequestErrorValidationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorsHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<RequestErrorValidationDto> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<RequestErrorValidationDto> requestErrorValidationDtos = new ArrayList<>();
        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        fieldErrorList.forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            RequestErrorValidationDto requestErrorValidationDto = new RequestErrorValidationDto(error.getField(), message);
            requestErrorValidationDtos.add(requestErrorValidationDto);
        });

        return requestErrorValidationDtos;
    }
}
