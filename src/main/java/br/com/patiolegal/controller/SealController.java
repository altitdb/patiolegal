package br.com.patiolegal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.SealRequestDTO;

@RestController
public class SealController {

	@PostMapping(path = "/api/v1/print/seal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FileIdentifierDTO> generate(@RequestBody SealRequestDTO request) {
	    FileIdentifierDTO fileIdentifier = new FileIdentifierDTO("mongodbid");
        return new ResponseEntity<>(fileIdentifier, HttpStatus.ACCEPTED);
	}
	
}
