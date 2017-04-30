package seemedics.service.triage.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.function.Predicate;

/**
 * @author victorp
 */
public class DefaultTraigeProtocolsSourceTest {
    private static final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void allJsonFilesAreLoaded(){
        DefaultTraigeProtocolsSource traigeProtocolsSource = new DefaultTraigeProtocolsSource();
        traigeProtocolsSource.pathToProtocols = Paths.get("src/test/resources/seemedics/model/triage/engine");

        traigeProtocolsSource.inputStreams()
                .allMatch(hasField("protocols"));
    }

    private Predicate<? super InputStream> hasField(String firstElement) {
        return is -> {
            JsonNode jsonNode = toJson(is);
            return jsonNode.findValue(firstElement) != null;
        };
    }

    public static JsonNode toJson(InputStream is){
        try {
            return mapper.readTree(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read json");
        }
    }
}
