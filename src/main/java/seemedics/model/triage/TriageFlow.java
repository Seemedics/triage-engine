package seemedics.model.triage;

import seemedics.dao.Entity;

/**
 * @author victorp
 */
public abstract class TriageFlow extends Entity{

    public TriageFlow(String id, String name) {
        super(id, name);
    }

    public abstract boolean isOutcome();

    public abstract ConditionalFlow toConditionalFlow();

    public abstract TriageOutcome toTriageOutcome();
}
