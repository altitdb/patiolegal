package br.com.patiolegal.service;

import java.io.InputStream;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patiolegal.domain.Protocol;
import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolDTO.ProtocolDTOBuilder;
import br.com.patiolegal.dto.SealRequestDTO;
import br.com.patiolegal.exception.ProtocolNotFoundException;
import br.com.patiolegal.reports.ReportUtils;
import br.com.patiolegal.repository.ProtocolRepository;

@Service
public class SealServiceBean implements SealService {

	private static final Logger LOG = LogManager.getLogger(SealServiceBean.class);
	@Autowired
	private ReportUtils reportUtils;
	
	@Autowired
	private ProtocolRepository repository;

	@Override
	public InputStream generate(SealRequestDTO request) {
		Optional<Protocol> protocol = repository.findByProtocol(request.getProtocol());
		if (protocol.isPresent()){
			ProtocolDTO dto = new ProtocolDTOBuilder()
								.withProtocol(protocol.get().getProtocol())
								.withAuthentication(protocol.get().getAuthentication())
								.build();
			return reportUtils.generateSealReport(request, dto);
		}
		LOG.error("Protocolo não encontrado em base de dados: " + request.getProtocol());
		throw new ProtocolNotFoundException();
	}

}
