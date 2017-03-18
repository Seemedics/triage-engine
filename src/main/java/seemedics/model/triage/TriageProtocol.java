package seemedics.model.triage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.*;
import seemedics.dao.Entity;
import seemedics.model.Fact;
import seemedics.triage.engine.NaiveTriageProtocolSelector;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static seemedics.util.CollectionUtil.toHashMap;

/**
 * @author victorp
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(converter=TriageProtocol.InitTrantiants.class)
public class TriageProtocol extends Entity {

    protected TriageProtocol() {
    }

    @Builder
    public TriageProtocol(String id, String name, String initialSymptomDescId,
                          TriageFlow flow,
                          @Singular Map<String, TriageFlow> subFlows,
                          @Singular Map<String, Fact> ans2facts) {
        super(id, name);
        this.initialSymptomDescId = initialSymptomDescId;
        this.flow = flow;
        this.subFlows = toHashMap(subFlows);
        this.ans2facts = toHashMap(ans2facts);
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
    private String initialSymptomDescId;


    /**
     * The main flow
     */
    @Getter
    @NonNull
    private  TriageFlow flow;

    /**
     * All sub Flows of this protocol <p>
     * Useful for continue the flow from the given step <p>
     */

    @NonNull
    @Getter(value = AccessLevel.PRIVATE)
    @JsonIgnore
    private HashMap<String,TriageFlow> subFlows;

    public Optional<TriageFlow> getSubFlow(String flowId){
        return Optional.ofNullable(subFlows.get(flowId));
    }

    /**
     * Each predef answer is mapped to specific fact
     */
    @NonNull
    @Getter(value = AccessLevel.PRIVATE)
    private HashMap<String,Fact> ans2facts;

    public Optional<Fact> factFromAnswer(String answerId){
        return Optional.ofNullable(ans2facts.get(answerId));
    }


    public static class InitTrantiants extends StdConverter<TriageProtocol,TriageProtocol>{

        @Override
        public TriageProtocol convert(TriageProtocol protocol) {
            protocol.subFlows = allSubFlows(protocol.flow);
            return protocol;
        }

        private HashMap<String, TriageFlow> allSubFlows(TriageFlow flow) {
            HashMap<String, TriageFlow> result = new HashMap<>();
            result.put(flow.getId(),flow);
            if (!flow.isOutcome()){
                flow.toConditionalFlow().getSubFlows().values()
                        .forEach(subFlow -> result.putAll(allSubFlows(subFlow)));

            }
            return result;
        }
    }
}
