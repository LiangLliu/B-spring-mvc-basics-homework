package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserIsExistException.class)
    public ResponseEntity<ErrorResult> handle(UserIsExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResult.builder()
                        .code(HttpStatus.CONFLICT.value())
                        .message(exception.getMessage())
                        .build());
    }

}
