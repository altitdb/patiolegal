package br.com.patiolegal.handler;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.patiolegal.dto.ErrorDTO;
import br.com.patiolegal.exception.AccessDeniedException;
import br.com.patiolegal.exception.BusinessException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionControllerAdvice {
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> notFoundException(final AccessDeniedException e) {
        ErrorDTO error = new ErrorDTO("Access Denied");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> notFoundException(final BusinessException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMediaTypeNotAcceptableException(final HttpMediaTypeNotAcceptableException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<ErrorDTO>(error, headers, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleHttpMediaTypeNotAcceptableException(final HttpMessageNotReadableException e) {
        InvalidFormatException ex = (InvalidFormatException) e.getCause();
        List<Reference> path = ex.getPath();
        Reference reference = path.stream().findFirst().get();
        @SuppressWarnings("unused")
        String fieldName = reference.getFieldName();
        ErrorDTO error = new ErrorDTO("Valor inválido");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorDTO> notFoundException(final InvalidFormatException e) {
        ErrorDTO error = new ErrorDTO(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> notFoundException(final Exception e) {
        ErrorDTO error = new ErrorDTO("Não foi possível completar sua requisição.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}