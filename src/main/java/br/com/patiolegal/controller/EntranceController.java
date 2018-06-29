package br.com.patiolegal.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.patiolegal.dto.ProtocolRequestDTO;
import br.com.patiolegal.dto.ProtocolResponseDTO;

@Controller
public class EntranceController {

    @RequestMapping(value = "/api/v1/entrance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ProtocolResponseDTO entrance(@RequestBody ProtocolRequestDTO request) {
        return new ProtocolResponseDTO("PROTOCOL2018");
    }

}
