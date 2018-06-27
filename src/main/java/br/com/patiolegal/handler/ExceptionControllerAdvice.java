package br.com.patiolegal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.patiolegal.exception.AccessDeniedException;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity notFoundException(final AccessDeniedException e) {
        return new ResponseEntity("{'message': 'Access Denied'}", HttpStatus.FORBIDDEN);
    }

}