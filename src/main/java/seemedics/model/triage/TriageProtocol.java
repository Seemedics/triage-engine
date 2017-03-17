package seemedics.model.triage;

import lombok.*;
import seemedics.dao.Entity;
import seemedics.model.Fact;
import seemedics.triage.engine.NaiveTriageProtocolSelector;

import java.util.Map;
import java.util.Optional;

/**
 * @author victorp
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TriageProtocol extends Entity {

    @Builder
    public TriageProtocol(String id, String name, String initialSymptomDescId,
                          TriageFlow flow,
                          @Singular Map<String, TriageFlow> subFlows,
                          @Singular Map<String, Fact> ans2facts) {
        super(id, name);
        this.initialSymptomDescId = initialSymptomDescId;
        this.flow = flow;
        this.subFlows = subFlows;
        this.ans2facts = ans2facts;
    }

    /**
     * Temp approach. <p>
     * In the future the selection will be implemented using rules engine!!!  <p>
     *
     * initialSymptom will be used by {@link NaiveTriageProtocolSelector}
     * in order to select corresponding protocol.
     *
     */
    @Getter
    @NonNull
    private final String initialSymptomDescId;


    /**
     * The main flow
     */
    @Getter
    @NonNull
    private final TriageFlow flow;

    /**
     * All sub Flows of this protocol <p>
     * Useful for continue the flow from the given step <p>
     */
    @NonNull
    @Getter(value = AccessLevel.PRIVATE)
    private final Map<String,TriageFlow> subFlows;

    public Optional<TriageFlow> getSubFlow(String flowId){
        return Optional.ofNullable(subFlows.get(flowId));
    }

    /**
     * Each predef answer is mapped to specific fact
     */
    @NonNull
    @Getter(value = AccessLevel.PRIVATE)
    private final Map<String,Fact> ans2facts;

    public Optional<Fact> factFromAnswer(String answerId){
        return Optional.ofNullable(ans2facts.get(answerId));
    }
}
