package seemedics.service.triage.engine;

import lombok.*;
import org.assertj.core.util.Sets;
import seemedics.model.Fact;

import java.util.HashSet;
import java.util.Set;

/**
 * @author victorp
 */
@Data
public class TriageNextIn {
    public TriageNextIn() {
    }

    /**
     * @param protocolId from the previous result
     * @param stepId from the previous result
     * @param knownFacts all known facts about corresponding user
     * @param answerId the answer to the question from the previous result
     * @return the result from this step (my be final or intermediate)
     */
    @Builder
    public TriageNextIn(String protocolId, String stepId, String answerId,
                        @Singular Set<Fact> knownFacts) {
        this.protocolId = protocolId;
        this.stepId = stepId;
        this.knownFacts = Sets.newHashSet(knownFacts);
        this.answerId = answerId;
    }

    @NonNull
    private String protocolId;

    @NonNull
    private String stepId;

    @NonNull
    private HashSet<Fact> knownFacts;

    @NonNull
    private String answerId;
}
