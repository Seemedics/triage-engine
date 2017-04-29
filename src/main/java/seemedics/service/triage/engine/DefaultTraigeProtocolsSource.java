package seemedics.service.triage.engine;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;

public class DefaultTraigeProtocolsSource implements TriageProtocolsSource
{
    @Value("${metadata.path}")
    protected Path pathToProtocols;

    @Override
    public Path get_pathToProtocols() {
        return pathToProtocols;
    }
}
