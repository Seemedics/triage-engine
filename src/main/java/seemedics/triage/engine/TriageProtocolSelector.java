package seemedics.triage.engine;

import seemedics.model.Fact;

import java.util.Set;

/**
 * @author victorp
 */
public interface TriageProtocolSelector {
    String select(Set<Fact> facts);

}
