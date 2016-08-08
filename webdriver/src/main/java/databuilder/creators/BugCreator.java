package databuilder.creators;

import databuilder.models.BugModel;
import databuilder.models.BugResponseModel;
import databuilder.models.BugUpdateModel;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class BugCreator
{

    private static RestTemplate restTemplate = new RestTemplate();
    private static String baseUrl = "http://52.17.197.56:8080/bugzilla";

    public static ResponseEntity<String> getBug(int id){
        return restTemplate.getForEntity(baseUrl + "/rest/bug/" + Integer.toString(id),
                String.class);
    }

    public static BugResponseModel createBug(BugModel payload) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BugModel> httpEntity = new HttpEntity<BugModel>(payload, requestHeaders);

        return restTemplate.exchange(baseUrl + "/rest/bug?login=admin@bugzilla.org&password=password", HttpMethod.POST, httpEntity, BugResponseModel.class).getBody();
    }

    public static ResponseEntity<String> updateBug(BugUpdateModel payload, int id) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BugUpdateModel> httpEntity = new HttpEntity<BugUpdateModel>(payload, requestHeaders);

        return restTemplate.exchange(baseUrl + "/rest/bug/" + Integer.toString(id) + "?login=admin@bugzilla.org&password=password", HttpMethod.PUT, httpEntity, String.class);
    }
}
