package seemedics.service.triage.engine;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import seemedics.model.triage.TriageProtocol;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
    @Value("${metadata.path}")
    protected Resource metadataResource;

    @Getter(AccessLevel.PRIVATE)
    private Map<String, TriageProtocol> protocols;

    @PostConstruct
    public void init() throws IOException {
        log.info("metadataResource: {}", metadataResource);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String metadataJson = FileCopyUtils.copyToString(new InputStreamReader(metadataResource.getInputStream()));
        TriageProtocol protocol = mapper.readValue(metadataJson, TriageProtocol.class);
        protocols = new HashMap<String, TriageProtocol>();
        protocols.put(protocol.getId(), protocol);

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
