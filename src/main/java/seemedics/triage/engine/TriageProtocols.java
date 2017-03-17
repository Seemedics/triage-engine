package seemedics.triage.engine;

import seemedics.model.triage.TriageProtocol;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This service provides a convenient way to access triage protocols
 *
 *
 * 1. By contract this service must be very fast (Most operations will be executed without I/O)
 * <p>
 *
 * 2. A given protocol is immutable forever. <p>
 * Meaning: by given protocol id this service will always return exactly the same protocol. <p>
 * It is critical property that ensures system consistency <p>
 * since a user may be in the middle of any protocol and any change <p>
 * in a protocol may lead to unpredictable behaviour. <p>
 * In order to implement this idea an update to any protocol must be <p>
 * implemented by creating new one that somehow will be signed by a newer version.
 *
 * @author victorp
 */
public interface TriageProtocols {

    /**
     * @return all existing protocols
     */
    Stream<TriageProtocol> stream();

    /**
     * @return protocol by its ID
     */
    Optional<TriageProtocol> get(String id);


}
