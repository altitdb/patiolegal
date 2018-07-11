package br.com.patiolegal.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.reports.ReportUtils;

@Service
public class SealServiceBean implements SealService {

	@Autowired
	ReportUtils reportUtils;

	@Override
	public InputStream generateSeal(SealRequestDTO request) {
		return reportUtils.generateSealReport(request);
	}

}
