package br.com.patiolegal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.ProtocolDTO;
import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.dto.ProtocolResponseDTO;
import br.com.patiolegal.dto.SearchEntranceRequestDTO;
import br.com.patiolegal.dto.SearchEntranceResponseDTO;
import br.com.patiolegal.service.EntranceService;

@RestController
public class EntranceController {

    @Autowired
    private EntranceService entranceService;

    @RequestMapping(value = "/api/v1/entrance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ProtocolResponseDTO entrance(@Valid @RequestBody ProtocolRequestDTO request) {
        String protocol = entranceService.save(request);
        return new ProtocolResponseDTO(protocol);
    }

    @GetMapping(value = "/api/v1/entrance")
    public List<ProtocolDTO> getAll() {
        return entranceService.find();
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/api/v1/entrance/search")
	public List<SearchEntranceResponseDTO> search(@RequestBody SearchEntranceRequestDTO request) {
		return entranceService.search(request);
	}

}
