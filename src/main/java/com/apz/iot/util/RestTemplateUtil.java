package com.apz.iot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class RestTemplateUtil {
    @SneakyThrows
    public static HttpEntity<String> createHttpEntity(Object entity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String entityJson = ow.writeValueAsString(entity);

        return new HttpEntity<>(entityJson, headers);
    }
}
