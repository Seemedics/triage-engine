package seemedics.triage.engine;

import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.triage.TriageProtocol;

import java.util.Map;
import java.util.Set;

/**
 * @author victorp
 */
public class DefaultTriageEngine implements TriageEngine {

    private final Map<String,TriageProtocol> triageProtocols;

    public DefaultTriageEngine(Map<String, TriageProtocol> triageProtocols) {
        this.triageProtocols = triageProtocols;
    }


    @Override
    public TriageResult start(Set<Fact> facts) {
        return null;
    }


    @Override
    public TriageResult next(String protocolId, String stepId, Set<Fact> facts, PredefAnswer answer) {
        return null;
    }
}
