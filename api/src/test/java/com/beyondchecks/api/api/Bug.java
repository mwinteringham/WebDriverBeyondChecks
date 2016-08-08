package com.beyondchecks.api.api;

import com.beyondchecks.api.payloads.BugPayload;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Bug {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String baseUrl = "http://52.209.233.55:8080/bugzilla";

    public static ResponseEntity<String> getBug(int id){
        return restTemplate.getForEntity(baseUrl + "/rest/bug/" + Integer.toString(id),
                String.class);
    }

    public static ResponseEntity<String> postBug(BugPayload payload) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BugPayload> httpEntity = new HttpEntity<BugPayload>(payload, requestHeaders);

        return restTemplate.exchange(baseUrl + "/rest/bug?login=admin@bugzilla.org&password=password", HttpMethod.POST, httpEntity, String.class);
    }
}
