package seemedics.triage.engine;

import lombok.Data;
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
    public final boolean isFinal;


    /**
     * NOTE: the pair: (protocolId,stepId) defines the the current location
     * of the protocol flow and must be used in order to continue the protocol
     */

    /**
     * The protocol ID that is currently used
     */
    public final String protocolId;


    /**
     * reference to the step within the flow
     * must be used when calling {@link TriageEngine#next}
     */
    public final String stepId;


    /**
     * question must NOT be empty if and only if isFinal = false
     */
    public final Optional<Question> question;




    /**
     * urgency must NOT be empty if and only if isFinal = true
     */
    public final Optional<Urgency> urgency;

    /**
     * New facts that were concluded in this invocation
     */
    public final Set<Fact> newFacts;
}
