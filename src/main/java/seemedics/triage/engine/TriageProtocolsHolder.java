package seemedics.triage.engine;

import seemedics.model.triage.TriageProtocol;

import java.util.stream.Stream;

/**
 * @author victorp
 */
public interface TriageProtocolsHolder {

    Stream<TriageProtocol> stream();
    TriageProtocol get(String id);


}
