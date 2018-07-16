package br.com.patiolegal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.PrintException;
import br.com.patiolegal.service.SealService;

@RestController
public class SealController {

	@Autowired
	private SealService service;

	@PostMapping(path = "/api/v1/print/seal", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generate(@RequestBody SealRequestDTO request) {
		ClassPathResource pdfFile = new ClassPathResource("seal.pdf");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		try{
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentLength(pdfFile.contentLength())
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(new InputStreamResource(service.generate(request)));
		} catch (IOException e) {
	        throw new PrintException();
	    }
	}
}
