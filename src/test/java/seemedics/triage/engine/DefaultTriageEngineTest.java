package seemedics.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ReturnsArgumentAt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import seemedics.model.triage.TriageProtocol;
import seemedics.model.triage.examples.SoreThroatProtocotData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
/**
 * @author victorp
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultTriageEngineTest {

    @MockBean
    private TriageProtocols triageProtocols;

    @Autowired
    private TriageEngine triageEngine;


    private static Map<String,TriageProtocol> testProtocols = SoreThroatProtocotData.protocols();


    @Test
    public void init() {
        given(triageProtocols.stream()).willReturn(testProtocols.values().stream());
        given(triageProtocols.get(anyString())).will(i -> {
            String protocolId = i.getArgumentAt(0, String.class);
            return Optional.ofNullable(testProtocols.get(protocolId));
        });

        log.info("protocol name: {}",triageProtocols.get(SoreThroatProtocotData.protocol().getId()).get().getName());

    }


}
