package seemedics.dao.humanattr;

import seemedics.model.metadata.HumanAttributeDescriptor;

/**
 * @author victorp
 */
public interface HumanAttributeDao {
    void add(HumanAttributeDescriptor symptomDescriptor);
    HumanAttributeDescriptor get(String id);
}
