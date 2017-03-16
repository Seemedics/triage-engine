package seemedics.model.metadata;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import seemedics.dao.Entity;

/**
 *  Metadata about any medical symptom as: <p>
 *  headache, red eye, fever etc
 * @author victorp
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MedSymptomDescriptor extends Entity{


    /**
     * We must add optional and mandatory fields here
     */

    @Builder
    private MedSymptomDescriptor(String id, String name) {
        super(id, name);
    }
}
