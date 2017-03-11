package seemedics.model.triage;

/**
 * @author victorp
 */
public interface TriageFlow {

    boolean isOutcome();

    ConditionalFlow toConditionalFlow();

    TriageOutcome toTriageOutcome();
}
