package seemedics.triage.engine;

import seemedics.model.Fact;
import seemedics.model.triage.TriageProtocol;

import java.util.Optional;
import java.util.Set;

/**
 * Selects corresponding protocol according to the given initial facts of a user.
 *
 * @author victorp
 */
public interface TriageProtocolSelector {

    /**
     * @return the most suitable protocol according to the given facts
     */
    Optional<TriageProtocol> select(Set<Fact> facts);
}
