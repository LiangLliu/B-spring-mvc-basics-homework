package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException exception) {
        String message = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResult.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(message)
                        .build());

    }


}
