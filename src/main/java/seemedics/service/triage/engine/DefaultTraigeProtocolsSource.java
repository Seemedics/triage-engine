package seemedics.service.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class DefaultTraigeProtocolsSource implements TriageProtocolsSource
{
    @Value("${protocol.path}")
    protected Path pathToProtocols;

    @Override
    public Stream<InputStream> inputStreams() {
        File file = pathToProtocols.toFile();
        validateExists(file);

        if (file.isDirectory()) {
            log.info("Going to load protocols from all files under directory: '{}'", file.getAbsolutePath());
            return fromDir(pathToProtocols);
        }else{
            log.info("Going to load protocols from file: '{}'", file.getAbsolutePath());
            return fromFile(pathToProtocols);
        }

    }

    private Stream<InputStream> fromFile(Path pathToProtocols) {
        return Lists.newArrayList(pathToProtocols.toAbsolutePath().toString()).stream()
                .map(DefaultTraigeProtocolsSource::toInputStream);
    }

    private static InputStream toInputStream(String pathToFile){
        try {
            return new FileInputStream(pathToFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to open FileInputStream from the file: "+pathToFile,e);
        }
    }

    private Stream<InputStream> fromDir(Path pathToProtocols) {
        try (Stream<Path> stream = Files.list(pathToProtocols)) {
            Set<InputStream> inStreams = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .filter(path -> path.endsWith(".json"))
                    .map(DefaultTraigeProtocolsSource::toInputStream)
                    .collect(Collectors.toSet());
            return inStreams.stream();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read directory content: "+pathToProtocols.toAbsolutePath(),e);
        }
    }

    private void validateExists(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(pathToProtocols+" is not a file or directory");
        }
    }
}
