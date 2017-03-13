package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import seemedics.model.dialog.Question;

import java.util.Map;

/**
 * @author victorp
 */

@Data
@Builder
public class ConditionalFlow implements TriageFlow {

    private Question question;

    private String id;

    /**
     * AnswerId -> Flow
     */
    @Singular
    private Map<String,TriageFlow> subFlows;

    public boolean isOutcome() { return  false; }

    public ConditionalFlow toConditionalFlow() { return this; }

    public TriageOutcome toTriageOutcome() { throw new IllegalStateException("This is not an outcome");}

}
