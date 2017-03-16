package seemedics.model.triage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import seemedics.model.Fact;
import seemedics.model.dialog.AnswerToFacts;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;
import seemedics.model.triage.examples.SoreThroatProtocotData;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * @author victorp
 */

@Slf4j
public class TriageModelTest {

    @Test
    public void initTriageProtocol(){


        TriageProtocol soreThroatProtocol = SoreThroatProtocotData.protocol();
        /**
         * Now we are going to simulate the triage engine
         */

        Fact initialFact = SoreThroatProtocotData.initialFact();

        Set<Fact> facts = new HashSet<>();
        facts.add(initialFact);

        log.info("Facts so far: {}",facts);

        ConditionalFlow flow1 = soreThroatProtocol.getFlow().toConditionalFlow();

        Question q1 = flow1.toConditionalFlow().getQuestion();

        Object[] options = q1.getChoices().toArray();
        log.info("Q: {}: {}", q1.getText(), q1.getChoices().stream().map(PredefAnswer::getText).toArray());

        PredefAnswer answer = (PredefAnswer) options[1];
        log.info("A: {}", answer.getText());

        facts.add(soreThroatProtocol.factFromAnswer(answer.getId()).get());
        log.info("Facts so far: {}",facts);

    }
}
