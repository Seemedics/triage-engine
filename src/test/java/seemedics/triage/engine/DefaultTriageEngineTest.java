package seemedics.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;
import seemedics.model.triage.TriageProtocol;
import seemedics.model.triage.examples.SoreThroatProtocotData;

import java.util.*;

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


        Set<Fact> allFacts = new HashSet<>();
        allFacts.addAll(SoreThroatProtocotData.initialFacts());

        log.info("protocol name: {}", triageProtocols.get(SoreThroatProtocotData.protocol().getId()).get().getName());

        TriageResult triageResult = triageEngine.start(SoreThroatProtocotData.initialFacts());

        Assert.assertFalse("Result #1 must not be final", triageResult.isFinal);

        int questionCount = 1;

        while (!triageResult.isFinal){
            String protocolId = triageResult.getProtocolId();
            String stepId = triageResult.getStepId();
            allFacts.addAll(triageResult.newFacts);
            Question question = triageResult.getQuestion().get();

            log.info("Question #{} {}",questionCount, question);

            PredefAnswer userAnswer = question.getChoices().stream().findFirst().get();
            log.info("User Answer #{} {}",questionCount,userAnswer);

            triageResult = triageEngine.next(protocolId,stepId,allFacts,userAnswer.getId());
        }


        //Final Result
        allFacts.addAll(triageResult.newFacts);

        log.info("Triage urgency: {}", triageResult.getUrgency().get());
        log.info("All facts from triage: {}",allFacts);




    }


}
