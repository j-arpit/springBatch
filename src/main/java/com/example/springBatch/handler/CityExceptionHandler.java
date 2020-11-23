package com.example.springBatch.handler;

import java.util.NoSuchElementException;

import com.example.springBatch.model.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handler01(){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Resource not found Error 404"),HttpStatus.NOT_FOUND);
    }
    
}
