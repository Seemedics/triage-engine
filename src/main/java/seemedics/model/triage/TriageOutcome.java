package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;

/**
 * @author victorp
 */
@Data
@Builder
public class TriageOutcome implements TriageFlow {
    /**
     * The urgency level and corresponding recommended actions
     */
    private final Urgency urgency;

    public boolean isOutcome() { return  true; }

    public ConditionalFlow toConditionalFlow() { throw new IllegalStateException("This is not an conditional flow"); }

    public TriageOutcome toTriageOutcome() { return this; }
}
