package br.com.patiolegal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.service.ProtocolService;

@RestController
public class ProtocolController {

	@Autowired
	private ProtocolService service;

	@Autowired
	private ReportUtils report;

	@PostMapping(value = "/api/v1/print/protocol", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generate(@RequestBody ProtocolRequestDTO request) {
		return report.printToPdf("protocol_" + request.getProtocol() + ".pdf", service.generate(request));
	}

}
