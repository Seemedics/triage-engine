package seemedics.service.triage.engine;

import lombok.AccessLevel;
import lombok.Getter;
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
import java.util.stream.Stream;

/**
 * Loads all protocols from the given file or directory
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
        LoadFileProtocol loadFileProtocol = new LoadFileProtocol();

        //TODO how to define file or directory.
        File f = metadataResource.getFile();
        if (!f.exists())
            throw new IOException(String.format("Not found file or directory %s", f.getAbsolutePath()));

        if (!f.isDirectory())
        {
            protocols = loadFileProtocol.LoadProtocols(metadataResource.getInputStream());
        } else {
            protocols = new HashMap<>();
            FileFilter jsonFilter = pathname -> pathname.getName().endsWith(".json");
            for (final File fileEntry: f.listFiles(jsonFilter)) {
                InputStream inputStream = new FileInputStream(fileEntry);
                Map<String, TriageProtocol> fileProtocols = loadFileProtocol.LoadProtocols(inputStream);
                protocols.putAll(fileProtocols);
            }
        }
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

