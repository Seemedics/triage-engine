package seemedics.triage.engine;

import seemedics.model.triage.TriageProtocol;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author victorp
 */
public interface TriageProtocols {

    Stream<TriageProtocol> stream();

    Optional<TriageProtocol> get(String id);


}
