package com.beyondchecks.api.api;

import com.beyondchecks.api.payloads.BugPayload;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Bug {

    private static RestTemplate restTemplate = new RestTemplate();
    private static String baseUrl = "https://landfill.bugzilla.org/bugzilla-5.0-branch";

    public static ResponseEntity<String> getBug(int id){
        return restTemplate.getForEntity(baseUrl + "/rest/bug/" + Integer.toString(id),
                String.class);
    }

    public static ResponseEntity<String> postBug(BugPayload payload) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BugPayload> httpEntity = new HttpEntity<BugPayload>(payload, requestHeaders);

        return restTemplate.exchange(baseUrl + "/rest/bug?login=mark@mwtestconsultancy.co.uk&password=c956N!xK@I9!OcoSbu6A", HttpMethod.POST, httpEntity, String.class);
    }
}
