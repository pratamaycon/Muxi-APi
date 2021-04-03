package com.pratamaycon.muxi;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pratamaycon.muxi.domain.model.Terminal;
import com.pratamaycon.muxi.domain.repository.TerminalRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest
@ExtendWith( SpringExtension.class )
@ActiveProfiles("test")
public class TerminalIT {
	
	Terminal terminal;
	
	@Autowired
	private TerminalRepository repository;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = 8080;
		RestAssured.basePath = "/api";
		
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarTerminal() {
		given() 
			.accept(ContentType.JSON)
		.when()
			.get("/v1/terminal")
		.then()
			.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarTerminalExistente() {
		given()
			.pathParam("logic", terminal.getLogic())
			.accept(ContentType.JSON)
		.when()
			.get("/v1/terminal/{logic}")
		.then()
			.statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarTerminal() {
		given() 
			.accept(ContentType.JSON)
		.when()
			.get("/v1/terminais")
		.then()
			.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarTerminal() {
		given()
			.log().all()
			.body("44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN")
			.contentType("text/html;charset=utf-8")
		.when()
			.log().all()
			.post("/v1/terminal")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	
	
	private Terminal createTerminal() {
    	return Terminal.builder()
    			.logic(44332211)
    			.serial("123")
    			.model("PWWIN")
    			.sam(0)
    			.ptid("F04A2E4088B")
    			.plat(4)
    			.verfm("8.00b3")
    			.mxr(0)
    			.mxf(16777216)
    			.verfm("PWWIN")
    			.build();
    }
	
	private void prepararDados() {
		terminal = createTerminal();
		
		repository.save(terminal);
	}
}
