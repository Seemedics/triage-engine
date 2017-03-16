package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import seemedics.dao.Entity;

/**
 * @author victorp
 */
@Data
public class TriageOutcome extends  TriageFlow {

    @Builder
    public TriageOutcome(String id, String name, Urgency urgency) {
        super(id, name);
        this.urgency = urgency;
    }

    /**
     * The urgency level and corresponding recommended actions
     */
    private final Urgency urgency;

    public boolean isOutcome() { return  true; }

    public ConditionalFlow toConditionalFlow() { throw new IllegalStateException("This is not an conditional flow"); }

    public TriageOutcome toTriageOutcome() { return this; }
}
