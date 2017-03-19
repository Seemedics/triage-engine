package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import seemedics.model.triage.TriageProtocol;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Temporary impl that lads all protocols from the given directory
 * from the local file system.
 *
 * @author victorp
 */
@Slf4j
@Service
public class LocalFilesTriageProtocols implements TriageProtocols {
    private Map<String,TriageProtocol> protocols;


    @Override
    public Stream<TriageProtocol> stream() {
        return protocols.values().stream();
    }

    @Override
    public Optional<TriageProtocol> get(String id) {
        return Optional.ofNullable(protocols.get(id));
    }
}
