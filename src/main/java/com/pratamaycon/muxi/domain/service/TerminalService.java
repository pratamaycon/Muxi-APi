package com.pratamaycon.muxi.domain.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratamaycon.muxi.domain.model.Terminal;
import com.pratamaycon.muxi.domain.repository.TerminalRepository;
import com.pratamaycon.muxi.domain.response.ErrorMensagem;
import com.pratamaycon.muxi.utils.SchemaValidation;

@Service
public class TerminalService {

	@Autowired
	TerminalRepository repository;

	/**
	 * salva uma entidade terminal *
	 * 
	 * @param Content-type text/html utf-8.
	 * @return um erro ou entidade em json persistida.
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	public String salvar(String payloadHTML) {
		SchemaValidation schemaValidation = new SchemaValidation();
		try {
			JSONObject jsonObject = transformHTMLtoJSON(payloadHTML);
			JSONObject schema = new JSONObject(SchemaValidation.JSON_SCHEMA);
			if (schemaValidation.validateJsonSchema(schema, jsonObject)) {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
				Terminal terminal = objectMapper.readValue(jsonObject.toString(), Terminal.class);
				if (repository.findByLogic(terminal.getLogic()) == null) {
					repository.save(terminal);
					return jsonObject.toString();
				} else {
					return new ErrorMensagem(SchemaValidation.PRIMARY_KEY_DUPLICADA).toString();
				}
			} else {
				return new ErrorMensagem(SchemaValidation.JSON_SCHEMA_INVALIDO).toString();
			}
		} catch (JsonProcessingException e) {
			return new ErrorMensagem(SchemaValidation.PAYLOAD_INVALIDO, e.getMessage()).toString();
		}
	}
	
	/**
	 * Estrutura os dados do body em json para serem persistidos no formato adequado	*
	 * @param Content-type text/html utf-8
	 * @return JSONObject.
	 */
	private JSONObject transformHTMLtoJSON(String payloadHTML) {

		String splitedPayLoad[] = payloadHTML.split(SchemaValidation.PAYLOADHTML_SEPARATOR);
		JSONObject objectMap = new JSONObject();

		objectMap.put(SchemaValidation.KEY_LOGIC, Integer.valueOf(splitedPayLoad[SchemaValidation.INDEX_LOGIC]));
		objectMap.put(SchemaValidation.KEY_SERIAL, splitedPayLoad[SchemaValidation.INDEX_SERIAL]);
		objectMap.put(SchemaValidation.KEY_MODEL, splitedPayLoad[SchemaValidation.INDEX_MODEL]);
		objectMap.put(SchemaValidation.KEY_SAM, Integer.valueOf(splitedPayLoad[SchemaValidation.INDEX_SAM]));
		objectMap.put(SchemaValidation.KEY_PTID, splitedPayLoad[SchemaValidation.INDEX_PTID]);
		objectMap.put(SchemaValidation.KEY_PLAT, Integer.valueOf(splitedPayLoad[SchemaValidation.INDEX_PLAT]));
		objectMap.put(SchemaValidation.KEY_VERSION, splitedPayLoad[SchemaValidation.INDEX_VERSION]);
		objectMap.put(SchemaValidation.KEY_MXR, Integer.valueOf(splitedPayLoad[SchemaValidation.INDEX_MXR]));
		objectMap.put(SchemaValidation.KEY_MXF, Integer.valueOf(splitedPayLoad[SchemaValidation.INDEX_MXF]));
		objectMap.put(SchemaValidation.KEY_VERFM, splitedPayLoad[SchemaValidation.INDEX_VERFM]);

		return objectMap;
	}
}
