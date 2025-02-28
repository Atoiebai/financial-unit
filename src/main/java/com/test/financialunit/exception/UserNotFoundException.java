package com.test.financialunit.exception;

public class UserNotFoundException extends RuntimeException{

    String message;

    public UserNotFoundException() {
       this.message = "User not found";
    }
}
