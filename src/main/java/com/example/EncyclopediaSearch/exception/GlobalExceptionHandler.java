package com.example.EncyclopediaSearch.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebInputException;

public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        return ResponseEntity.badRequest().body("Missing request parameter: " + name);
    }

    @ExceptionHandler(EncyclopediaApiException.class)
    public ResponseEntity<String> handleYouTubeApiException(EncyclopediaApiException ex) {
        return ResponseEntity.status(502).body(ex.getMessage());
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<String> handleValidationException(ServerWebInputException ex) {
        return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body("Internal server error");
    }
}
