package br.com.patiolegal.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.service.SealService;

@Controller
public class SealController {

	@Autowired
	private SealService service;
	
	@RequestMapping(value = "/api/v1/print/seal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody InputStream generateSeal(@RequestBody SealRequestDTO request){
		return service.generateSeal(request);
	}
	
}
