package br.com.patiolegal.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.service.SealService;

@RestController
public class SealController {

	@Autowired
	private SealService service;
	
	@PostMapping(path = "/api/v1/print/seal")
	public InputStream generate(@RequestBody SealRequestDTO request){
		return service.generate(request);
	}
	
}
