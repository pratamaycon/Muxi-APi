package com.pratamaycon.muxi.domain.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pratamaycon.muxi.domain.model.Terminal;

@SpringBootTest
@ExtendWith( SpringExtension.class )
@ActiveProfiles("test")
public class TerminalRepositoryTest {
	
    @Autowired
    TerminalRepository repository;
    
    @Test
	public void deveEncontrarUmTerminalPorLogic() {
    	//cenario
    	Integer logic = 44332211;
    	
		//acao
		Terminal terminal = repository.findByLogic(logic);

		//verificao
		assertNotNull(terminal);
	}
}
