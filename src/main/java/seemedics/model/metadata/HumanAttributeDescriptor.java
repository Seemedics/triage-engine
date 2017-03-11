package seemedics.model.metadata;

import lombok.Data;
import lombok.EqualsAndHashCode;
import seemedics.dao.Entity;

/**
 * Metadata about any stable human attribute as gander, birth date, genetics, skin color etc
 * @author victorp
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class HumanAttributeDescriptor extends Entity {
}
