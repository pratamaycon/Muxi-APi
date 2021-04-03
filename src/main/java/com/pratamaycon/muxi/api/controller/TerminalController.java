package com.pratamaycon.muxi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pratamaycon.muxi.domain.model.Terminal;
import com.pratamaycon.muxi.domain.repository.TerminalRepository;
import com.pratamaycon.muxi.domain.service.TerminalService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/api/")
public class TerminalController {
    
    @Autowired
    private TerminalRepository repository;
    
    @Autowired
    private TerminalService service;
    
    @GetMapping(value = {"v1/terminal"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Terminal> buscarTodos() {
        return repository.findAll();
    }
    
    @GetMapping(value = {"v1/terminal/{logic}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Optional<Terminal> buscarPeloId(@PathVariable("logic") Integer logic) {
        return repository.findByLogic(logic);
    }
    
    @PostMapping(value = {"v1/terminal"}, consumes = {"text/html; charset=utf-8"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public String criarTerminal(@RequestBody @NotNull String body) {
		return service.salvar(body);
    }
    
    @PutMapping(value = {"v1/terminal/{logic}"}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public String atualizarTerminal(@RequestBody @NotNull Terminal terminal, @PathVariable("logic") Integer logic) {
        return service.atualizar(terminal, logic);
    }
    
}
