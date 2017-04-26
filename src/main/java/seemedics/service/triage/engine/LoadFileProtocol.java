package seemedics.service.triage.engine;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static java.util.function.Function.identity;
import static seemedics.util.CollectionUtil.toHashMap;

public class LoadFileProtocol{
    ObjectMapper mapper = new ObjectMapper();

    public LoadFileProtocol(){
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public Map<String, TriageProtocol> LoadProtocols(InputStream inputStream) throws IOException {
        try (Reader reader = new InputStreamReader(inputStream))
        {
            JsonSerializableMetadata metadata = mapper.readValue(reader, JsonSerializableMetadata.class);
            return metadata.protocols;
        }
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
