package br.com.patiolegal.controller;

import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.service.SealService;

@RestController
public class SealController {

    @Autowired
    private ReportUtils reportUtils;
    @Autowired
    private SealService sealService;
    
    @PostMapping(path = "/api/v1/print/seal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileIdentifierDTO> generate(@Valid @RequestBody SealRequestDTO request) {
        FileIdentifierDTO fileIdentifier = sealService.generate(request);
        return new ResponseEntity<>(fileIdentifier, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/api/v1/print/seal/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadSeal(@PathVariable("id") String id) {
        InputStream inputStream = sealService.get(id);
        return reportUtils.printToPdf("lacre.pdf", inputStream);
    }
}
