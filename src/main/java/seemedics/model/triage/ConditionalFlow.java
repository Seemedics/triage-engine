package seemedics.model.triage;

import lombok.*;
import seemedics.dao.Entity;
import seemedics.model.dialog.Question;

import java.util.Map;
import java.util.Optional;

/**
 * @author victorp
 */

@Data
@Getter
public class ConditionalFlow extends  TriageFlow {

    private Question question;

    @Builder
    public ConditionalFlow(String id, String name, Question question,
                           @Singular Map<String, TriageFlow> subFlows) {
        super(id, name);
        this.question = question;
        this.subFlows = subFlows;
    }

    /**
     * AnswerId -> Flow
     */

    @Getter(AccessLevel.PRIVATE)
    private Map<String,TriageFlow> subFlows;

    public Optional<TriageFlow> subFlow(String answerId){
        return Optional.ofNullable(subFlows.get(answerId));
    }

    public boolean isOutcome() { return  false; }

    public ConditionalFlow toConditionalFlow() { return this; }

    public TriageOutcome toTriageOutcome() { throw new IllegalStateException("This is not an outcome");}

}
