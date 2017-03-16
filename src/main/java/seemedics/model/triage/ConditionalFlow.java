package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;
import seemedics.dao.Entity;
import seemedics.model.dialog.Question;

import java.util.Map;

/**
 * @author victorp
 */

@Data
@Getter
public class ConditionalFlow extends Entity implements TriageFlow {

    private Question question;

    @Builder
    public ConditionalFlow(String id, String name, Question question,
                           @Singular Map<String, TriageFlow> subFlows) {
        super(id, name);
        this.question = question;
        this.subFlows = subFlows;
    }

    /**
     * AnswerId -> FlowId
     */

    private Map<String,TriageFlow> subFlows;

    public boolean isOutcome() { return  false; }

    public ConditionalFlow toConditionalFlow() { return this; }

    public TriageOutcome toTriageOutcome() { throw new IllegalStateException("This is not an outcome");}

}
