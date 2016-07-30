package com.beyondchecks.api;

import com.beyondchecks.api.api.Bug;
import com.beyondchecks.api.payloads.BugPayload;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class ApiTest {

    @Test
    public void checkForBug(){
        ResponseEntity<String> result = Bug.getBug(1);

        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void checkForBugCreation(){
        BugPayload bugToCreate = new BugPayload.BugPayloadBuilder()
                .setProduct("TestProduct")
                .setComponent("TestComponent")
                .setSummary("testing")
                .setVersion("unspecified")
                .setOp_sys("Windows")
                .setRep_platform("PC").build();

        ResponseEntity<String> result = Bug.postBug(bugToCreate);

        assertEquals(result.getStatusCodeValue(), 200);
    }

}
