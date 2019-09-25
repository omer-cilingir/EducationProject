package com.education.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonMapper() {
        throw new IllegalAccessError("Cannot access JsonMapper.class");
    }

    public static String toJsonString(Object o) {

        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Json converting error --> {}", o);
            jsonString = o.toString();
        }
        return jsonString;
    }

    public static <T> T toObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }
}
