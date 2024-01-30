package io.github.gulybyte.todo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ITTestUtils extends ArgumentsMatchersTodo {

    public static <T> HttpEntity<T> createRequestEntity(T obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(obj, headers);
    }

    public static  String createJson(String key, String value) {
        return "{\""+key+"\": \""+value+"\"}";
    }

}
