package com.example.springBatch.handler;

import java.util.NoSuchElementException;

import com.example.springBatch.model.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityExceptionHandler {

    Logger logger = LoggerFactory.getLogger( this.getClass());

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handler01(){
        logger.error("Resource not found Error 404, Using Controller Advice");
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Resource not found Error 404, Using Controller Advice"),HttpStatus.NOT_FOUND);
    }
    
}
