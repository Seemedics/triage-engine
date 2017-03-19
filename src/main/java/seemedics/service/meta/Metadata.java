package seemedics.service.meta;

import seemedics.model.metadata.MedSymptomDescriptor;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author victorp
 */
public interface Metadata {
    Optional<MedSymptomDescriptor> getSymptomDescriptor(String id);
    Stream<MedSymptomDescriptor> stream();
}
