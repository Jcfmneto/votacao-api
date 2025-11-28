package com.julio.votacao.exception;


import com.julio.votacao.exception.model.ApiError;
import com.julio.votacao.exception.type.AssociadoAlreadyExistsException;
import com.julio.votacao.exception.type.AssociadoNotFoundException;
import com.julio.votacao.exception.type.PautaNotFoundException;
import com.julio.votacao.exception.type.SessaoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcpetionHandler {

    @ExceptionHandler({PautaNotFoundException.class, SessaoNotFoundException.class, AssociadoNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(AssociadoAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAssociadoAlreadyExists(AssociadoAlreadyExistsException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Requisição inválida");

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
