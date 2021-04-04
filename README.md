
<img align="right" src="http://www.muxi.com.br/portugues/wp-content/uploads/sites/2/thegem-logos/logo_d06ebca587fae12271450c25cf2e3654_1x.png">

# DESAFIO TÉCNICO DE BACKEND

## Descrição

<p align="justify">
O Desafio backend é uma das etapas do processo seletivo da muxi. O objetivo desse desafio é a construção de uma Api Rest com algumas operações de GRUD, tais como: busca, cadastro e atualização em uma entidade denonimada <i style="itatic">terminal</i>. </p>

## TÉCNOLOGIAS ESCOLHIDAS

- JAVA 11
- MAVEN
- Spring Boot 2.4.0
- JPA
- Sqlite in Memory
- REST
- JSON-Schema
- Tomcat
- RestAssured
- JUNIT 5
- POSTMAN
- IDE ECLIPSE

## JUSTIFICATIVA DAS TECNLOGIAS UTILIZADAS

<p align="justify">
Após ler atentamente os requisitos do desafio, optei por escolher a tecnologia a qual eu possuia mais experiência o JAVA versão 11. Além disso, para facilitar o desenvolvimento dos endpoints REST foi escolhido o framework web Spring Boot versão 2.4.0 juntamente com banco de dados SQLlite IN MEMORY que consta como dica no documento do desafio. </p>

<p align="justify">
A metologia de design de api adotada foi o DDD que tem como foco o que está acontecendo no domínio da aplicação. Em outras palavras, e como o nome sugere, o design é centrado na lógica de negócios (domínio) do software. Como foi dito no domain temos: Modelo de Dominio, Serviços de dominio e Repositório de Dados. Esse três elementos são essenciais no que podemos dizer a respeito do domínio da aplicação. Além disso, ainda temos os <i style="itatic">resources</i> que ficam em um pacote a parte do domnínio da aplicação sendo denominado como <i style="itatic">api</i>. </p>

<p align="justify">
Outra estratégia utilizada durante o desenvolvimento da API foi o TDD, seguindo a idéia de primeiro construir os testes antes de implementar a solução de produção. </p>

<p align="justify">
Algumas técnologias como servidor de aplicação tomcat (embarcado), gerenciador de pacotes MAVEN e o client para fazer as requisições http POSTMAN foram utilizados no desenvolvimento dessa API. </p>

## PRÉ REQUISITOS PARA RODAR ESSE PROJETO

- JAVA 11
- MAVEN
- Client POSTMAN ou similiares para testar os endpoints

## CONFIGURAÇÕES DATABASE
Configurações SQLlite
em application.properties:
```
spring.jpa.database-platform=com.pratamaycon.muxi.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:sqlite:memory:myDb?cache=shared
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username=pxp
spring.datasource.password=password
spring.jpa.show-sql=true
```

## INSTRUÇÕES DE USO

### COMANDO DE EXECUÇÃO

Necessário rodar o comando (dentro do diretório do projeto)

`mvn clean install`

<p align="justify">
Obs: Não foi possível manter a API em nuvem pois serviços como heroku, por exemplo, não suportam banco em memória como SQLlite. No caso Herouku ele exclui o armazenamento local, não permitindo que as informações sejam persistidas localmente. 
</p>

[Link para o github com uma explicação](https://stackoverflow.com/questions/27663075/download-sqlite-database-from-heroku)

### URLS DOS ENDPOINTS

**GET**
URL do endpoint que retorna uma lista de terminais:

`http://localhost:8080/api/v1/terminal`

**GET**
URL do endpoint que retorna um terminal /{logic}:

`http://localhost:8080/api/v1/terminal/46332211`

**POST**
URL do endpoint que cria um terminal:

`http://localhost:8080/api/v1/terminal`

**PUT**
URL do endpoint que atualiza um terminal:

`http://localhost:8080/api/v1/terminal/46332211`

Obs: Na Raiz do Projeto, há uma pasta com uma coleção exportada do postman durante desevolvimento da API.

## TESTES AUTOMIZADOS

É possível rodar todos os testes, incluindo os de integração. Simplesmente apertando 

`JUnit test`

na sua Ide de preferência.

### TESTES UNITÁRIOS 

- Executar os testes Unitários (dentro do diretório do projeto)

`mvn test` 

```java
	...

    	@Test
	public void deveEncontrarUmTerminalPorLogic() {
    		//cenario
    		Integer logic = 46332211;
    	
		//acao
		Optional<Terminal> terminalOp = repository.findByLogic(logic);
		Terminal terminal = terminalOp.get();

		//verificao
		assertNotNull(terminal);
	}
	
	...
```

### TESTES DE INTEGRAÇÃO

- Executar o teste de integração 

Obs: Para que os testes funcionem corretamente a aplicação precisa estar rodando.

Obs: Testes de Intgeração necessitam de dados para não quebrarem.

```java
  @SpringBootTest
  @ExtendWith( SpringExtension.class )
  @ActiveProfiles("test")
  public class TerminalIT {
  
    	    ...
    
    	    @Test
	    public void deveRetornarStatus200_QuandoConsultarTerminal() {
		      given() 
			      .accept(ContentType.JSON)
		      .when()
			      .get("/v1/terminal")
		      .then()
			      .statusCode(HttpStatus.SC_OK);
	    }
 
 	    ...
  }

```
