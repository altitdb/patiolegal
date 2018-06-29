package br.com.patiolegal.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.patiolegal.dto.ErrorDTO;
import br.com.patiolegal.exception.AccessDeniedException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> notFoundException(final AccessDeniedException e) {
        ErrorDTO error = new ErrorDTO("Access Denied");
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.FORBIDDEN);
    }

}