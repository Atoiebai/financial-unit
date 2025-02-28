package com.test.financialunit.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    private ResponseEntity handleApplicationException(final ApplicationException ex) {
        log.error("ApplicationException : {}", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new BaseResponse(ex.getMessage(), ex.getCode()));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error("MethodArgumentNotValidException = {}", ex.getMessage());
        String errorMessage = ex.getFieldError().getDefaultMessage();
        BaseResponse response = new BaseResponse(errorMessage, 10);
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException = {}", ex.getMessage());
        BaseResponse response = new BaseResponse("", 11);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
