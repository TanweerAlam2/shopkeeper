package com.example.shopkeeper.config;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String,Object>> handle(Exception ex){
   return ResponseEntity.badRequest().body(Map.of("timestamp", Instant.now().toString(), "error", ex.getMessage()==null?"Request failed":ex.getMessage()));
 }
}
