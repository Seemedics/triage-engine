package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import seemedics.model.Fact;

import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(locations = "classpath:seemedics/service/triage/engine/protocolsTriageEngineTestConfig.properties")
public class RealTriageEngineTest {
    @Autowired
    private TriageEngine triageEngine;

    @Test
    public void init() {
        Assert.assertNotNull(triageEngine);

        Long timePeriodMills = new Long(10);
        Map<String, String> values = new HashMap<>();
        values.put("temperature", "37-39");
        Fact initialFact = new Fact("fact-fever-37-39", "Body Temperature 37-39", "sym-sore-throat", "EXISTS", timePeriodMills, values);
        TriageStartIn triageStartIn = new TriageStartIn(new HashSet(Arrays.asList(initialFact)));
        TriageResult triageResult = triageEngine.start(triageStartIn);

        Assert.assertFalse("Result #1 must not be final", triageResult.isFinal());
    }
}
