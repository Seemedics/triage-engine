package seemedics.model.triage;

import lombok.*;

/**
 * @author victorp
 */
@Setter(AccessLevel.PRIVATE)
@Data
public class TriageOutcome extends  TriageFlow {

    protected TriageOutcome() {
    }

    public TriageOutcome(String id, String name) {
        super(id, name);
    }

    @Builder
    public TriageOutcome(String id, String name, Urgency urgency) {
        super(id, name);
        this.urgency = urgency;
    }

    /**
     * The urgency level and corresponding recommended actions
     */
    private Urgency urgency;

    public boolean isOutcome() { return  true; }

    public ConditionalFlow toConditionalFlow() { throw new IllegalStateException("This is not an conditional flow"); }

    public TriageOutcome toTriageOutcome() { return this; }
}
