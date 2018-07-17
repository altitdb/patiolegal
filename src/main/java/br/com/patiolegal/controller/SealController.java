package br.com.patiolegal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.service.SealService;

@RestController
public class SealController {

	@Autowired
	private SealService service;

	@Autowired
	private ReportUtils report;

	@PostMapping(path = "/api/v1/print/seal", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generate(@RequestBody SealRequestDTO request) {
		return report.printToPdf("seal.pdf", service.generate(request));
	}
}
