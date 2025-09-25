package br.com.tinnova.TinnovaVeiculo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(VeiculoIdNotFoundException.class)
    public ResponseEntity<Map<String, String>> veiculoIdNotFound(VeiculoIdNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Id invalido");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
