package com.pratamaycon.muxi.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pratamaycon.muxi.domain.service.TerminalService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class SchemaValidationTest {

	@Autowired
	TerminalService terminalService;

	SchemaValidation schemaValidation;

	@Test
	public void deveValidarSchemaJson() {
		try {
			schemaValidation = new SchemaValidation();
			final String TEST_JSON = "{\"serial\":\"123\",\"mxr\":0,\"VERFM\":\"PWWIN\",\"model\":\"KGWIN\",\"mxf\":16777216,\"logic\":46332211,\"ptid\":\"F04A2E4088B\",\"plat\":4,\"version\":\"8.00b3\",\"sam\":0}";
			
			JSONObject schema = new JSONObject(SchemaValidation.JSON_SCHEMA);
			boolean validSchema = schemaValidation.validateJsonSchema(schema, new JSONObject(TEST_JSON));
			assertTrue(validSchema);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void naoDeveValidarSchemaJson() throws JSONException {
		final String TEST_JSON = "{\"serial\":\"123\",\"mxr\":0,\"VERFM\":\"PWWIN\",\"model\":\"KGWIN\",\"mxf\":16777216,\"logic\":46332211,\"ptid\":\"F04A2E4088B\",\"plat\":4,\"version\":\"8.00b3\",\"sam\":\"F04A2E4088B\"}";
		schemaValidation = new SchemaValidation();
		JSONObject schema = new JSONObject(SchemaValidation.JSON_SCHEMA);
		boolean validSchema = schemaValidation.validateJsonSchema(schema, new JSONObject(TEST_JSON));
		assertFalse(validSchema);
	}

}
