package seemedics.model.triage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import seemedics.model.Entity;

/**
 * @author victorp
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.MINIMAL_CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConditionalFlow.class),
        @JsonSubTypes.Type(value = TriageOutcome.class)
})
public abstract class TriageFlow extends Entity{

    protected TriageFlow() {
    }

    public TriageFlow(String id, String name) {
        super(id, name);
    }

    public abstract boolean isOutcome();

    public abstract ConditionalFlow toConditionalFlow();

    public abstract TriageOutcome toTriageOutcome();
}
