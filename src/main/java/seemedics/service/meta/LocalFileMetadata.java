package seemedics.service.meta;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import seemedics.model.metadata.MedSymptomDescriptor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static seemedics.util.CollectionUtil.toHashMap;

/**
 * Temporary impl that lads all metadata from the given local file.
 *
 * @author victorp
 */

@Slf4j
@Service
public class LocalFileMetadata implements Metadata {

    @Value("${metadata.path}")
    protected Resource metadataResource;

    @PostConstruct
    public void init() throws IOException {
        log.info("metadataResource: {}", metadataResource);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


        String metadataJson = FileCopyUtils.copyToString(new InputStreamReader(metadataResource.getInputStream()));
        metadata =  mapper.readValue(metadataJson, JsonSerializableMetadata.class);
        log.info("metadata: {}", metadata.toString());
    }

    @Getter(AccessLevel.PRIVATE)
    private JsonSerializableMetadata metadata;

    public Optional<MedSymptomDescriptor> getSymptomDescriptor(String id){
        return Optional.ofNullable(metadata.symptomDescriptors.get(id));
    }

    @Override
    public Stream<MedSymptomDescriptor> stream() {
        return metadata.symptomDescriptors.values().stream();
    }

    /**
     * @return Id -> MedSymptomDescriptor map
     */
    private static Map<String, MedSymptomDescriptor> toMap(Set<MedSymptomDescriptor> symptomDescriptors) {
        return symptomDescriptors.stream()
                .collect(Collectors.toMap(MedSymptomDescriptor::getId, identity()));
    }

    @Data
    public static class JsonSerializableMetadata{

        private JsonSerializableMetadata() {
        }

        @Builder
        public JsonSerializableMetadata(@Singular Set<MedSymptomDescriptor> symptomDescriptors) {
            this.symptomDescriptors = toHashMap(toMap(symptomDescriptors));
        }
        private HashMap<String,MedSymptomDescriptor> symptomDescriptors;
    }
}
