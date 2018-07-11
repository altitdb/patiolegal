package br.com.patiolegal.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.patiolegal.exception.PrintException;

@Controller
public class PrintController {

    @RequestMapping(value = "/api/v1/print/protocol/{protocol}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadProtocol(@PathParam("protocol") String protocol) {
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
    
    @RequestMapping(value = "/api/v1/print/seal/{protocol}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadSeal(@PathParam("protocol") String protocol) {
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
