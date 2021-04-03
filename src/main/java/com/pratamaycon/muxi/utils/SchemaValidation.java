package com.pratamaycon.muxi.utils;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

public class SchemaValidation {
	
    //HASH MAP KEY
    public static final String KEY_LOGIC = "logic";
    public static final String KEY_SERIAL = "serial";
    public static final String KEY_MODEL = "model";
    public static final String KEY_SAM = "sam";
    public static final String KEY_PTID = "ptid";
    public static final String KEY_PLAT = "plat";
    public static final String KEY_VERSION = "version";
    public static final String KEY_MXR = "mxr";
    public static final String KEY_MXF = "mxf";
    public static final String KEY_VERFM = "VERFM";

    //PAYLOAD PARSE INDEX
    public static final int INDEX_LOGIC = 0;
    public static final int INDEX_SERIAL = 1;
    public static final int INDEX_MODEL = 2;
    public static final int INDEX_SAM = 3;
    public static final int INDEX_PTID = 4;
    public static final int INDEX_PLAT = 5;
    public static final int INDEX_VERSION = 6;
    public static final int INDEX_MXR = 7;
    public static final int INDEX_MXF = 8;
    public static final int INDEX_VERFM = 9;
    public static final String PAYLOADHTML_SEPARATOR = ";";

    /**
     * Descrição das mensagens de erro
     */
    public static final String PRIMARY_KEY_DUPLICADA = "Já existe uma entidade com esse mesmo atributo <logic>.";
    public static final String ENTIDADE_NAO_ENCONTRADA = "Nenhuma entidade com esse atributo <logic> foi encontrada.";
    public static final String ERRO_DESCONHECIDO = "Erro desconhecido";
    public static final String PAYLOAD_INVALIDO = "Payload inválido, o formato pode estar errado";
    public static final String JSON_SCHEMA_INVALIDO = "Seu payload não corresponde ao schema json.";
    

    // JSON-SCHEMA
    public static final String JSON_SCHEMA = "{\n" +
            "  \"title\": \"Terminal\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"logic\": {\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"serial\": {\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"sam\": {\n" +
            "      \"type\": \"integer\",\n" +
            "      \"minimum\": 0\n" +
            "    },\n" +
            "    \"ptid\": {\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"plat\": {\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"version\": {\n" +
            "      \"type\": \"string\"\n" +
            "    },\n" +
            "    \"mxr\": {\n" +
            "      \"type\": \"integer\"\n" +
            "    },\n" +
            "    \"VERFM\": {\n" +
            "      \"type\": \"string\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\": [\n" +
            "    \"logic\",\n" +
            "    \"serial\",\n" +
            "    \"model\",\n" +
            "    \"version\"\n" +
            "  ]\n" +
            "}";
    
	/**
	 * Recebe o body transformado em objeto json e json-schema e a partir pratica
	 * Validação do esquema json * 
	 * @param obj JSON OBJECT
	 * @return retorna um booleano
	 */
	public boolean validateJsonSchema(JSONObject jsonSchema, JSONObject payloadJson) {
		try {
			SchemaLoader loader = SchemaLoader.builder().schemaJson(jsonSchema).draftV6Support().build();
			Schema schema = loader.load().build();
			schema.validate(payloadJson);
			return true;
		} catch (ValidationException e) {
			return false;
		}
	}
}
