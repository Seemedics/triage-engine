package seemedics.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import seemedics.model.Fact;
import seemedics.model.Facts;
import seemedics.service.triage.engine.TriageResult;

import java.io.IOException;

/**
 * @author victorp
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:seemedics/controller/rest/triageResourceTestConfig.properties")
public class TriageResourceTest {
    String REST_TRAIGE_ROOT_PATH = "/v0/triage";
    String REST_TRAIGE_START_PATH = REST_TRAIGE_ROOT_PATH+"/start";


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void verifyRoot() {
        ResponseEntity<String> response =  restTemplate.getForEntity(REST_TRAIGE_ROOT_PATH, String.class);
        Assert.assertTrue(REST_TRAIGE_ROOT_PATH + " must return success on GET",response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void verifyStart() throws IOException {
        Fact soreThroatFact = Fact.builder()
                .id("fact-sore-throat")
                .ref("sym-sore-throat")
                .name("Sore Throat")
                .status(Fact.Status.EXISTS)
                .build();

        Facts facts = Facts.builder().
                fact(soreThroatFact).build();

        ResponseEntity<String> response =  restTemplate.postForEntity(REST_TRAIGE_START_PATH, facts, String.class);
        Assert.assertTrue(REST_TRAIGE_ROOT_PATH + " must return success on POST",response.getStatusCode().is2xxSuccessful());
        log.info("Start response: {}", response.getBody());
        TriageResult triageResult = objectMapper.readValue(response.getBody(), TriageResult.class);
        Assert.assertEquals("unexpected step id","flow-sore-throat",triageResult.getStepId());
        Assert.assertEquals("unexpected protocol id","prot-int-test-01",triageResult.getProtocolId());

    }
}
