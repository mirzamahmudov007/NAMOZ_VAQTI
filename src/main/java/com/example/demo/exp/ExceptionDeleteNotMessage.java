package com.example.demo.exp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionDeleteNotMessage extends RuntimeException{

    @ExceptionHandler(value = ExceptionDeleteNotMessage.class)
    public ResponseEntity<Object> exception(ExceptionDeleteNotMessage exception) {
    return new ResponseEntity<>("O'chiriladigan habar idsi topilmadi" , HttpStatus.BAD_REQUEST);
    }

}
