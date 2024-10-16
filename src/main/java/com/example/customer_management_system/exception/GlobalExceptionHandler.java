package com.example.customer_management_system.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Handling generic exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
         body.put("error", "An error occured");
         body.put("message", ex.getMessage());
         body.put("timestamp", LocalDateTime.now());

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }


    // handling the specific exception: CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException e){
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Customer Not Found");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
