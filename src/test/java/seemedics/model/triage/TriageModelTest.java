package seemedics.model.triage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import seemedics.model.Fact;
import seemedics.model.dialog.AnswerToFacts;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;

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


        Fact symFeverAbove40 = Fact.builder()
                .id("sym-fever")
                .name("fever")
                .value("temperature","40-42")
                .build();


        Fact symFeverAbove39 = Fact.builder()
                .id("sym-fever")
                .name("fever")
                .value("temperature","39-40")
                .build();

        Fact symFeverAbove37 = Fact.builder()
                .id("sym-fever")
                .name("fever")
                .value("temperature", "37-39")
                .build();

        Fact symNoFever = Fact.builder()
                .id("sym-fever")
                .name("fever")
                .value("temperature", "36-37")
                .build();

        Fact soreThroat = Fact.builder()
                .id("sym-sore-throat")
                .name("Sore Throat")
                .build();


        PredefAnswer feverAbove37C = PredefAnswer.builder()
                .id("ans-fever=37-39")
                .text("37-39 C")
                .build();

        PredefAnswer feverAbove39C = PredefAnswer.builder()
                .id("ans-fever=39-40")
                .text("39-40 C")
                .build();


        PredefAnswer feverAbove40C = PredefAnswer.builder()
                .id("ans-fever=40-42")
                .text("40-42 C")
                .build();

        PredefAnswer noFever = PredefAnswer.builder()
                .id("ans-no-fever")
                .text("I have no fever")
                .build();


        AnswerToFacts answerToFacts =
            AnswerToFacts.builder()
                .ans2facts(noFever, symNoFever.asSet())
                .ans2facts(feverAbove40C, symFeverAbove40.asSet())
                .ans2facts(feverAbove37C, symFeverAbove37.asSet())
                .ans2facts(feverAbove39C, symFeverAbove39.asSet())
                .build();

        Question doYouHaveFever = Question.builder()
                .id("q-do-u-have-fever")
                .text("Do you have fever?")
                .choice(feverAbove37C)
                .choice(feverAbove39C)
                .choice(feverAbove40C)
                .choice(noFever)
                .build();


        PredefAnswer yesWhitePatches = PredefAnswer.builder()
                .id("ans-yes-white-patches")
                .text("White patches")
                .imageUrlOpt(Optional.of("http://www.foodpyramid.com/wp-content/uploads/2013/07/white-spots-on-back-of-throat-1.jpg"))
                .build();


        PredefAnswer noWhitePatches = PredefAnswer.builder()
                .id("ans-no-white-patches")
                .text("White patches")
                .imageUrlOpt(Optional.of("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTApo2EAy6exxQPG18lo2-eibmgPWiUWQAurlsGl3xaVbS0bjwuwQ"))
                .build();



        Question doYouSeeWhitePatches = Question.builder()
                .id("q-do-u-see-white-patches")
                .text("Do you see white patches around the throat?")
                .choice(yesWhitePatches)
                .choice(noWhitePatches)
                .build();


        Urgency callEmergency = Urgency.builder()
                .level(1)
                .interpratation("Call emergency now.")
                .build();

        Urgency docWithin24h = Urgency.builder()
                .level(2)
                .interpratation("I recommend you to see a doctor within 24 hours.")
                .build();

        Urgency pharmacist = Urgency.builder()
                .level(1)
                .interpratation("At this point I recommend you to get recommendation for a pharmacist.")
                .build();

        TriageOutcome flowForFeverAbove40 = TriageOutcome.builder()
                .urgency(callEmergency)
                .build();

        TriageOutcome flowForWhitePatches = TriageOutcome.builder()
                .urgency(docWithin24h)
                .build();

        TriageOutcome flowForNoWhitePatches = TriageOutcome.builder()
                .urgency(pharmacist)
                .build();

        ConditionalFlow flowForFever36to40 = ConditionalFlow.builder()
                .question(doYouSeeWhitePatches)
                .subFlow(yesWhitePatches.getId(), flowForWhitePatches)
                .subFlow(noWhitePatches.getId(), flowForNoWhitePatches)
                .build();

        TriageFlow soreThroatProtocolFlow = ConditionalFlow.builder()
                .question(doYouHaveFever)
                .subFlow(feverAbove40C.getId(), flowForFeverAbove40)
                .subFlow(feverAbove37C.getId(), flowForFever36to40)
                .subFlow(feverAbove39C.getId(), flowForFever36to40)
                .subFlow(noFever.getId(), flowForFever36to40)
                .build();



        TriageProtocol soreThroatProtocol = TriageProtocol.builder()
                .initialFact(soreThroat)
                .flow(soreThroatProtocolFlow)
                .build();


        /**
         * Now we are going to simulate the triage engine
         */

        Fact initialFact = soreThroat;

        Set<Fact> facts = new HashSet<>();
        facts.add(initialFact);

        log.info("Facts so far: {}",facts);


        Assert.assertTrue("soreThroat is expected to be the initial facts for this protocol", soreThroatProtocol.getInitialFacts().contains(soreThroat));

        ConditionalFlow flow1 = soreThroatProtocol.getFlow().toConditionalFlow();

        Question q1 = flow1.toConditionalFlow().getQuestion();

        Object[] options = q1.getChoices().toArray();
        log.info("Q: {}: {}", q1.getText(), q1.getChoices().stream().map(PredefAnswer::getText).toArray());

        PredefAnswer answer = (PredefAnswer) options[1];
        log.info("A: {}", answer.getText());

        facts.addAll(answerToFacts.getFactsByAns().get(answer));
        log.info("Facts so far: {}",facts);

    }
}
