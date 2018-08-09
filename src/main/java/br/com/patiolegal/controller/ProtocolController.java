package br.com.patiolegal.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.FileIdentifierDTO;
import br.com.patiolegal.dto.ProtocolRequestDTO;

@RestController
public class ProtocolController {

	@PostMapping(value = "/api/v1/print/protocol")
	public ResponseEntity<FileIdentifierDTO> generate(@RequestBody @Valid ProtocolRequestDTO request) {
	    FileIdentifierDTO fileIdentifier = new FileIdentifierDTO("mongodbid");
	    return new ResponseEntity<>(fileIdentifier, HttpStatus.ACCEPTED);
	}

}
