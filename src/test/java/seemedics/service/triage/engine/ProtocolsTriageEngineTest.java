package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author victorp
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
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