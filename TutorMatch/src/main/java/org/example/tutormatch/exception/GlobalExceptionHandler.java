package org.example.tutormatch.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<?> handle(RuntimeException ex) {
                String msg = ex.getMessage() != null ? ex.getMessage().toLowerCase() : "";
                Map<String, Object> response = new HashMap<>();
                response.put("message", ex.getMessage());
                if (msg.contains("not found") || msg.contains("nie istnieje")) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
                if (msg.contains("email already exists") || msg.contains("email już zajęty") || msg.contains("email exists")) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                }
                if (msg.contains("access denied") || msg.contains("brak autoryzacji") || msg.contains("forbidden")) {
                        response.put("errors", Map.of("auth", "Brak autoryzacji"));
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
                return ResponseEntity.badRequest().body(response);
        }



        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                        "message", "Brak autoryzacji"
                ));
        }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        error.getDefaultMessage()
                ));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Błąd walidacji");
        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }
}