package com.awey.dscomerce.controllers.handlers;

import com.awey.dscomerce.dto.CustomError;
import com.awey.dscomerce.dto.ValidationError;
import com.awey.dscomerce.services.DatabaseException;
import com.awey.dscomerce.services.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(request.getRequestURI(),e.getMessage(), status.value(),Instant.now());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(request.getRequestURI(),e.getMessage(), status.value(),Instant.now());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(request.getRequestURI(),"Dados Inválidos", status.value(),Instant.now());

        for (FieldError field: e.getBindingResult().getFieldErrors()){
            err.addError(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}