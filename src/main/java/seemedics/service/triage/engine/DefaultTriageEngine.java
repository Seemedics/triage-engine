package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seemedics.model.Fact;
import seemedics.model.triage.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * @author victorp
 */
@Slf4j
@Service
public class DefaultTriageEngine implements TriageEngine {

    @Autowired
    private TriageProtocols triageProtocols;

    @Autowired
    private TriageProtocolSelector protocolSelector;




    @Override
    public TriageResult start(Set<Fact> facts) {
        TriageProtocol protocol = selectProtocol(facts);
        TriageFlow flow = protocol.getFlow();
        return next(protocol.getId(), flow, Collections.EMPTY_SET);
    }



    @Override
    public TriageResult next(String protocolId, String stepId, Set<Fact> knownFacts, String answerId) {
        TriageProtocol protocol = getTriageProtocol(protocolId);
        ConditionalFlow currentFlow = getConditionalFlow(protocol, stepId);

        TriageFlow nextFlow = subFlow(currentFlow, answerId);
        Fact newFact = fact(protocol, answerId);

        return next(protocol.getId(), nextFlow, newFact.asSet());
    }

    private static TriageResult next(String protocolId, TriageFlow flow, Set<Fact> newFacts) {
        TriageResult.TriageResultBuilder result = TriageResult.builder()
                .protocolId(protocolId)
                .stepId(flow.getId())
                .newFacts(newFacts);

        if (flow.isOutcome()){
            TriageOutcome outcome = flow.toTriageOutcome();
            result.isFinal(true).urgency(Optional.of(outcome.getUrgency()));
        }else{
            ConditionalFlow conditionalFlow = flow.toConditionalFlow();
            result.isFinal(false)
                    .question(Optional.of(conditionalFlow.getQuestion()))
                    .urgency(Optional.empty());
        }

        return result.build();
    }

    private static Fact fact(TriageProtocol protocol, String answerId) {
        return protocol.factFromAnswer(answerId).orElseThrow(() -> new IllegalStateException("Corresponding answer was not found in protocol, protocol id:" + protocol.getId() + ", answer id:" + answerId));
    }

    private static TriageFlow subFlow( ConditionalFlow currentFlow, String answerId) {
        return currentFlow.subFlow(answerId).orElseThrow(() -> new IllegalStateException("Corresponding answer was not found in current sub flow, sub flow id:" + currentFlow.getId() + ", answer id:" + answerId));
    }


    private static ConditionalFlow getConditionalFlow(TriageProtocol protocol, String stepId) {
        TriageFlow flow =  protocol.getSubFlow(stepId).orElseThrow(() -> new IllegalArgumentException("Step (sub flow) not found in protocol id:" + protocol.getId() + ", step id:" + stepId));
        if (flow.isOutcome()){
            throw new IllegalStateException("StepId does NOT refer to ConditionalFlow");
        }

        return flow.toConditionalFlow();
    }



    private TriageProtocol selectProtocol(Set<Fact> facts) {
        return protocolSelector.select(facts).orElseThrow(() -> new RuntimeException("Corresponding protocol was not found"));
    }

    private TriageProtocol getTriageProtocol(String protocolId) {
        return triageProtocols.get(protocolId).orElseThrow(() -> new IllegalArgumentException("Protocol not found, id:" + protocolId));
    }


}
