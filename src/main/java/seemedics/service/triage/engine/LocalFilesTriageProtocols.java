package seemedics.service.triage.engine;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import seemedics.model.triage.TriageProtocol;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static seemedics.util.CollectionUtil.toHashMap;

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

        //TODO how to define file or directory.
        File f = (metadataResource).getFile();
        if (!f.exists())
            throw new IOException(String.format("Not found file or directory %s", f.getAbsolutePath()));

        if (!f.isDirectory())
        {
            protocols = LoadProtocols(metadataResource.getInputStream());
        } else {
            protocols = new HashMap<>();
            for (final File fileEntry: f.listFiles()) { //TODO implement filter
                InputStream inputStream = new FileInputStream(fileEntry);
                Map<String, TriageProtocol> fileProtocols = LoadProtocols(inputStream);
                protocols.putAll(fileProtocols);
            }
        }
        log.info("metadata: {}", protocols.toString());
    }

    private Map<String, TriageProtocol> LoadProtocols(InputStream inputStream) throws IOException {
        //TODO remove to class's field
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try (Reader reader = new InputStreamReader(inputStream))
        {
            JsonSerializableMetadata metadata = mapper.readValue(reader, JsonSerializableMetadata.class);
            return metadata.protocols;
        }
    }

    @Override
    public Stream<TriageProtocol> stream() {
        return protocols.values().stream();
    }

    @Override
    public Optional<TriageProtocol> get(String id) {
        return Optional.ofNullable(protocols.get(id));
    }

    private static Map<String, TriageProtocol> toMap(Set<TriageProtocol> protocols) {
        return protocols.stream()
            .collect(Collectors.toMap(TriageProtocol::getId, identity()));
    }
    @Data
    public static class JsonSerializableMetadata{
        private JsonSerializableMetadata() {
        }

        @Builder
        public JsonSerializableMetadata(@Singular Set<TriageProtocol> protocols) {
            this.protocols = toHashMap(toMap(protocols));
        }
        private HashMap<String,TriageProtocol> protocols;
    }
}
