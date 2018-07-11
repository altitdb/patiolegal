package br.com.patiolegal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.patiolegal.dto.ArrestOrganDTO;
import br.com.patiolegal.service.ArrestOrganService;

@RestController
public class ArrestOrganController {

    @Autowired
    private ArrestOrganService arrestOrganService;

    @GetMapping(path = "/api/v1/part")
    public List<ArrestOrganDTO> findAll() {
        return arrestOrganService.findAll();
    }
    
}
