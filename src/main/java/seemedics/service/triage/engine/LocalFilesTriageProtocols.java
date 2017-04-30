package seemedics.service.triage.engine;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seemedics.model.triage.TriageProtocol;
import seemedics.serializer.ProtocolSerializer;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

/**
 * Loads all protocols from the given file or directory
 * from the local file system.
 *
 * @author victorp
 */
@Slf4j
@Service
public class LocalFilesTriageProtocols implements TriageProtocols {

    @Autowired
    protected TriageProtocolsSource triageProtocolsSource;

    @Getter(AccessLevel.PRIVATE)
    private Map<String, TriageProtocol> protocols;

    @PostConstruct
    public void init() throws IOException {
        protocols = triageProtocolsSource.inputStreams()
                        .flatMap(ProtocolSerializer::loadProtocols)
                        .collect(Collectors.toMap(TriageProtocol::getId, identity()));
        log.info("metadata: {}", protocols.toString());
    }

    @Override
    public Stream<TriageProtocol> stream() {
        return protocols.values().stream();
    }

    @Override
    public Optional<TriageProtocol> get(String id) {
        return Optional.ofNullable(protocols.get(id));
    }
}

