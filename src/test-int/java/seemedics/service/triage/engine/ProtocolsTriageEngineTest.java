package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author victorp
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:seemedics/service/triage/engine/protocolsTriageEngineTestConfig.properties")
public class ProtocolsTriageEngineTest {

    @Autowired
    private LocalFilesTriageProtocols triageProtocols;

    @Autowired
    private TriageEngine triageEngine;

    @Test
    public void init() {
        Assert.assertNotNull(triageEngine);
    }
}