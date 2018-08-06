package br.com.patiolegal.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.exception.PrintException;

@RestController
public class PrintController {

    @GetMapping(value = "/api/v1/print/protocol/{protocol}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<InputStreamResource> downloadProtocol(@PathVariable("protocol") String protocol) {
        ClassPathResource pdfFile = new ClassPathResource("protocol.pdf");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(pdfFile.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(pdfFile.getInputStream()));
        } catch (IOException e) {
            throw new PrintException();
        }
    }
    
    @GetMapping(value = "/api/v1/print/seal/{protocol}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<InputStreamResource> downloadSeal(@PathVariable("protocol") String protocol) {
        ClassPathResource pdfFile = new ClassPathResource("seal.pdf");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(pdfFile.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(pdfFile.getInputStream()));
        } catch (IOException e) {
            throw new PrintException();
        }
    }
    
}
