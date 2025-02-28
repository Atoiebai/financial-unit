package com.test.financialunit.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class ApplicationException extends RuntimeException {

    int code;
    String message;
    HttpStatus status;

    protected ApplicationException(int code, final String message, final HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
    }

}
