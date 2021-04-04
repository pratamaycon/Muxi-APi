package com.pratamaycon.muxi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.pratamaycon.muxi.domain.response.ErrorMensagem;
import com.pratamaycon.muxi.domain.service.TerminalService;
import com.pratamaycon.muxi.utils.SchemaValidation;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/api/")
public class TerminalController {

	@Autowired
	private TerminalRepository repository;

	@Autowired
	private TerminalService service;

	@GetMapping(value = { "v1/terminal" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Terminal> buscarTodos() {
		return repository.findAll();
	}

	@GetMapping(value = { "v1/terminal/{logic}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> buscarPeloId(@PathVariable("logic") Integer logic) {
		Optional<Terminal> terminalOp = repository.findByLogic(logic);

		if (terminalOp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorMensagem(SchemaValidation.ENTIDADE_NAO_ENCONTRADA).toString());
		}

		return ResponseEntity.status(HttpStatus.OK).body(terminalOp.get());
	}

	@PostMapping(value = { "v1/terminal" }, consumes = { "text/html; charset=utf-8" }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> criarTerminal(@RequestBody @NotNull String body) {
		try {
			String terminalCriado = service.salvar(body);
			return ResponseEntity.status(HttpStatus.CREATED).body(terminalCriado);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorMensagem(SchemaValidation.PAYLOAD_INVALIDO).toString());
		}
	}

	@PutMapping(value = { "v1/terminal/{logic}" }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> atualizarTerminal(@RequestBody @NotNull Terminal terminal, @PathVariable("logic") Integer logic) {
		Optional<Terminal> terminalOp = repository.findByLogic(logic);
        if (terminalOp.isEmpty()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorMensagem(SchemaValidation.ENTIDADE_NAO_ENCONTRADA).toString());
        }
        
    	try {
    		String terminalAtualizado = service.atualizar(terminal, logic);
    		if (terminalAtualizado.contains("message")) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(terminalAtualizado);
    		}
			return ResponseEntity.status(HttpStatus.OK).body(terminalAtualizado);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorMensagem(SchemaValidation.PAYLOAD_INVALIDO).toString());
		}
	}

}
