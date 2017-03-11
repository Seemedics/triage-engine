package seemedics.model.metadata;

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

}
