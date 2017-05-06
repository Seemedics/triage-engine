package seemedics.service.triage.engine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import seemedics.model.Fact;
import seemedics.model.dialog.Question;
import seemedics.model.triage.Urgency;

import java.util.Optional;
import java.util.Set;

/**
 * @author victorp
 */
@Data
public class TriageResult {
    public TriageResult() {
    }

    @Builder
    public TriageResult(boolean isFinal, String protocolId, String stepId, Question question, Urgency urgency, Set<Fact> newFacts) {
        this.isFinal = isFinal;
        this.protocolId = protocolId;
        this.stepId = stepId;
        this.question = question;
        this.urgency = urgency;
        this.newFacts = newFacts;
    }

    @Getter(AccessLevel.PUBLIC)
    private boolean isFinal;


    /**
     * NOTE: the pair: (protocolId,stepId) defines the the current location
     * of the protocol flow and must be used in order to continue the protocol
     */

    /**
     * The protocol ID that is currently used
     */
    @Getter(AccessLevel.PUBLIC)
    private String protocolId;


    /**
     * reference to the step within the flow
     * must be used when calling {@link TriageEngine#next}
     */
    @Getter(AccessLevel.PUBLIC)
    private String stepId;


    /**
     * question must NOT be empty if and only if isFinal = false
     */
    @Getter(AccessLevel.NONE)
    private Question question;

    public Optional<Question> getQuestion(){
        return Optional.ofNullable(question);
    }



    /**
     * urgency must NOT be empty if and only if isFinal = true
     */
    @Getter(AccessLevel.NONE)
    public Urgency urgency;


    public Optional<Urgency> getUrgency(){
        return Optional.ofNullable(urgency);
    }

    /**
     * New facts that were concluded in this invocation
     */
    @Getter(AccessLevel.PUBLIC)
    private Set<Fact> newFacts;
}
