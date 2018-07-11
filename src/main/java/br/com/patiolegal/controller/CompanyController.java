package br.com.patiolegal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.CompanyDTO;
import br.com.patiolegal.service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService service;

	@RequestMapping(value = "/api/v1/company/details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody CompanyDTO getCompanyDetails(@PathVariable String id) {
		CompanyDTO companyDTO = service.findById(id);
		return companyDTO;
	}

}
