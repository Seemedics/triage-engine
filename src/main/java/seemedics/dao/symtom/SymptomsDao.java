package seemedics.dao.symtom;

import seemedics.model.metadata.MedSymptomDescriptor;

/**
 * @author victorp
 */
public interface SymptomsDao {
    void add(MedSymptomDescriptor symptomDescriptor);
    MedSymptomDescriptor get(String id);
}
