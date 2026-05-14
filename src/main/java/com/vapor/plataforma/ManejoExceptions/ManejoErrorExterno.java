package com.vapor.plataforma.ManejoExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ManejoErrorExterno {


   @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericError(Exception e) {
        ApiError error = new ApiError(500,"Error en la integridad de los datos ingresados",e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}