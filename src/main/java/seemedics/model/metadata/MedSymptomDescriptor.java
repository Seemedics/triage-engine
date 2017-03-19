package seemedics.model.metadata;

import lombok.*;
import seemedics.model.Entity;

/**
 *  Metadata about any medical symptom as: <p>
 *  headache, red eye, fever etc
 * @author victorp
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MedSymptomDescriptor extends Entity{


    private MedSymptomDescriptor() {
    }

    /**
     * We must add optional and mandatory fields here
     */

    @Builder
    private MedSymptomDescriptor(String id, String name) {
        super(id, name);
    }
}
