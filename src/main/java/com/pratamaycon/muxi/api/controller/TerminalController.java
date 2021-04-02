package com.pratamaycon.muxi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pratamaycon.muxi.domain.model.Terminal;
import com.pratamaycon.muxi.domain.repository.TerminalRepository;

@RestController
@RequestMapping("/terminais")
public class TerminalController {
    
    @Autowired
    private TerminalRepository repository;
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Terminal> buscarTodos() {
        return repository.findAll();
    }
    
    @GetMapping(value = {"/{logic}"})
    @ResponseStatus(HttpStatus.OK)
    public Terminal buscarPeloId(@PathVariable("logic") Integer logic) {
        return repository.findByLogic(logic);
    }
    
}
