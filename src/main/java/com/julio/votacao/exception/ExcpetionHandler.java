package com.julio.votacao.exception;


import com.julio.votacao.exception.model.ApiError;
import com.julio.votacao.exception.type.PautaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcpetionHandler {
    @ExceptionHandler(PautaNotFoundException.class)
    public ResponseEntity<?> handlePautaNotFound(PautaNotFoundException ex) {
        ApiError apiError = new ApiError(ex.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
