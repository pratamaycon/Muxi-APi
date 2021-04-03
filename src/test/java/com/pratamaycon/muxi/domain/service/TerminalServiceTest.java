package com.pratamaycon.muxi.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pratamaycon.muxi.domain.model.Terminal;
import com.pratamaycon.muxi.domain.repository.TerminalRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TerminalServiceTest {

	@MockBean
	TerminalRepository repository;

	@Autowired
	TerminalService service;

	private Terminal createTerminal() {
		return Terminal.builder().logic(46332211).serial("123").model("PWWIN").sam(0).ptid("F04A2E4088B").plat(4)
				.verfm("8.00b3").mxr(0).mxf(16777216).verfm("PWWIN").build();
	}

	@Test
	public void deveSalvarUmTerminal() {
		String payload = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
		Terminal terminal = createTerminal();
		Mockito.when(repository.save(terminal)).thenReturn(new Terminal());

		String savedTerminal = service.salvar(payload);

		assertThat(savedTerminal).isNotNull();

	}

	@Test
	public void deveAtualizarrUmTerminal() {
		Integer logic = 46332211;

		Terminal terminal = createTerminal();

		Mockito.when(repository.save(terminal)).thenReturn(new Terminal());

		String updatedTerminal = service.atualizar(terminal, logic);
		
		System.out.println(updatedTerminal);

		assertThat(updatedTerminal).isNotNull();
	}

}
