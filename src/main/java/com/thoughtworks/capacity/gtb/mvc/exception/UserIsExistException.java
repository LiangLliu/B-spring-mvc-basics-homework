package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserIsExistException extends RuntimeException{
    public UserIsExistException(String message) {
        super(message);
    }
}
