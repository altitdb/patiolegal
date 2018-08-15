package br.com.patiolegal.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.NotFoundException;

@RestController
public class SealController {

    @PostMapping(path = "/api/v1/print/seal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileIdentifierDTO> generate(@RequestBody SealRequestDTO request) {
        FileIdentifierDTO fileIdentifier = new FileIdentifierDTO("mongodbid");
        return new ResponseEntity<>(fileIdentifier, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/api/v1/print/seal/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadSeal(@PathVariable("id") String protocol) {
        ClassPathResource pdfFile = new ClassPathResource("seal.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        try {
            return ResponseEntity.ok().headers(headers).contentLength(pdfFile.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(pdfFile.getInputStream()));
        } catch (IOException e) {
            throw new NotFoundException();
        }
    }
}
