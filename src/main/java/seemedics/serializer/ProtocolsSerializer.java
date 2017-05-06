package seemedics.serializer;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import seemedics.model.triage.TriageProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static seemedics.util.CollectionUtil.toHashMap;

/**
 * @author victorp
 */
public class ProtocolsSerializer {

    public static Stream<TriageProtocol> deserializeProtocols(InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream)) {
            JsonSerializableMetadata metadata = ModelSerializer.mapper.readValue(reader, JsonSerializableMetadata.class);
            return metadata.protocols.values().stream();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load protocols from the given inputStream: "+ inputStream.toString(),e);
        }
    }


    @Data
    public static class JsonSerializableMetadata{
        private JsonSerializableMetadata() {
        }

        @Builder
        public JsonSerializableMetadata(@Singular Set<TriageProtocol> protocols) {
            this.protocols = toHashMap(toMap(protocols));
        }

        private Map<String, TriageProtocol> toMap(Set<TriageProtocol> protocols) {
            return protocols.stream()
                    .collect(Collectors.toMap(TriageProtocol::getId, identity()));
        }

        private HashMap<String,TriageProtocol> protocols;
    }
}
