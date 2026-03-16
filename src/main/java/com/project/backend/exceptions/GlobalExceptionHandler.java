package com.project.backend.exceptions;

import com.project.backend.exceptions.user.EmailIsEmptyException;
import com.project.backend.exceptions.user.PasswordIsEmptyException;
import com.project.backend.exceptions.user.UserDidntRegisterException;
import com.project.backend.exceptions.user.UserDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* User Exceptions */
    @ExceptionHandler
    public ResponseEntity<?> handleEmailIsEmpty(EmailIsEmptyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handlePasswordIsEmpty(PasswordIsEmptyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserDidntRegister(UserDidntRegisterException exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserDoesntExist(UserDoesntExistException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", exception.getMessage()));
    }

    /* Food Exception */

    /* Ingredient Exceptions */
}
