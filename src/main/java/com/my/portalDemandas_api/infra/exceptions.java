package com.my.portalDemandas_api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity EntityNotFound404(RuntimeException ex){
        InformarExceptionsDto dto = new InformarExceptionsDto("404", ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    public record InformarExceptionsDto(String erro, String mensagem){}
}
