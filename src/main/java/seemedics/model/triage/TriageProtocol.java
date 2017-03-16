package seemedics.model.triage;

import lombok.*;
import seemedics.dao.Entity;
import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.metadata.MedSymptomDescriptor;

import java.util.Map;
import java.util.Set;

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
     * initialSymptom will be used by {@link seemedics.triage.engine.SimpleTriageProtocolSelector}
     * in order to select corresponding protocol.
     *
     */
    @NonNull
    private final String initialSymptomDescId;


    /**
     * The main flow
     */
    @NonNull
    private final TriageFlow flow;

    /**
     * All sub Flows of this protocol <p>
     * Useful for continue the flow from the given step <p>
     */
    @NonNull
    private final Map<String,TriageFlow> subFlows;

    /**
     * Each predef answer is mapped to specific fact
     */
    @NonNull
    private final Map<String,Fact> ans2facts;

}
