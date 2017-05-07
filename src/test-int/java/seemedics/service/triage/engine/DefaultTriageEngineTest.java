package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;
import seemedics.model.triage.TriageProtocol;
import seemedics.model.triage.examples.SoreThroatProtocotData;

import java.util.*;

import static org.mockito.BDDMockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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

        TriageResult triageResult = triageEngine.start(new TriageStartIn(SoreThroatProtocotData.initialFacts()));

        Assert.assertFalse("Result #1 must not be final", triageResult.isFinal());

        int questionCount = 1;

        while (!triageResult.isFinal()){
            String protocolId = triageResult.getProtocolId();
            String stepId = triageResult.getStepId();
            allFacts.addAll(triageResult.getNewFacts());
            Question question = triageResult.getQuestion().get();

            log.info("Question #{} {}",questionCount, question);

            PredefAnswer userAnswer = question.getChoices().stream().findFirst().get();
            log.info("User Answer #{} {}", questionCount, userAnswer);

            TriageNextIn triageNextIn = TriageNextIn.builder()
                    .protocolId(protocolId)
                    .answerId(userAnswer.getId())
                    .knownFacts(allFacts)
                    .stepId(stepId)
                    .build();
            triageResult = triageEngine.next(triageNextIn);
            questionCount++;
        }

        //Final Result
        allFacts.addAll(triageResult.getNewFacts());

        log.info("Triage urgency: {}", triageResult.getUrgency().get());
        log.info("All facts from triage: {}",allFacts);
    }
}
