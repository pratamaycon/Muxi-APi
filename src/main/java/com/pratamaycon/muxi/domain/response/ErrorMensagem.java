package com.pratamaycon.muxi.domain.response;

import java.time.Instant;

import org.json.JSONObject;

public class ErrorMensagem extends JSONObject {

    public ErrorMensagem(String msg, String stacktrace) {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        put("message", msg);
        put("stacktrace", stacktrace);
        put("timestamp", timeStampMillis);
    }

    public ErrorMensagem(String msg) {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        put("message", msg);
        put("timestamp", timeStampMillis);
    }
}
