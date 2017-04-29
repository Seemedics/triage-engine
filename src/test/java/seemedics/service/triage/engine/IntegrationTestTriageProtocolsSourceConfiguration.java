package seemedics.service.triage.engine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by igor-z on 29-Apr-17.
 */
@Configuration
public class IntegrationTestTriageProtocolsSourceConfiguration {

    public class IntegrationTestTriageProtocolsSource implements TriageProtocolsSource{
        @Override
        public Path get_pathToProtocols() {
            return Paths.get("src/test/resources/seemedics/model/triage/protocolsDirectory");
        }
    }

    @Bean
    public TriageProtocolsSource get_TriageProtocolsSource() {
        return new IntegrationTestTriageProtocolsSource();
    }
}
