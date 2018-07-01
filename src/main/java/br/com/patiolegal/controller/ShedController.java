package br.com.patiolegal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.patiolegal.dto.ShedDTO;
import br.com.patiolegal.service.ShedService;

@Controller
public class ShedController {

	@Autowired
	ShedService service;
	
	@RequestMapping(name = "/api/v1/shed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ShedDTO> listAllSheds() {
		return service.listAllSheds();
	}

}
