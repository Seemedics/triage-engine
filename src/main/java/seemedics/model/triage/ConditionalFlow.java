package seemedics.model.triage;

import lombok.*;
import seemedics.model.dialog.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static seemedics.util.CollectionUtil.toHashMap;

/**
 * @author victorp
 */

@Data
@Getter
public class ConditionalFlow extends  TriageFlow {
    protected ConditionalFlow() {
    }

    public ConditionalFlow(String id, String name) {
        super(id, name);
    }

    private Question question;

    @Builder
    public ConditionalFlow(String id, String name, Question question,
                           @Singular Map<String, TriageFlow> subFlows) {
        super(id, name);
        this.question = question;
        this.subFlows = toHashMap(subFlows);
    }

    /**
     * AnswerId -> Flow
     */

    private HashMap<String,TriageFlow> subFlows;

    public Optional<TriageFlow> subFlow(String answerId){
        return Optional.ofNullable(subFlows.get(answerId));
    }

    public boolean isOutcome() { return  false; }

    public ConditionalFlow toConditionalFlow() { return this; }

    public TriageOutcome toTriageOutcome() { throw new IllegalStateException("This is not an outcome");}

}
