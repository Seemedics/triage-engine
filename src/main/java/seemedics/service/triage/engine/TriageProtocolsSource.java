package seemedics.service.triage.engine;

import java.io.InputStream;
import java.util.stream.Stream;


public interface TriageProtocolsSource {
    /**
     * Each InputStream represents a content that can be mapped to a valid set of protocols <P>
     * Normally the content will be a json <P>
     */
    Stream<InputStream> inputStreams();
}
