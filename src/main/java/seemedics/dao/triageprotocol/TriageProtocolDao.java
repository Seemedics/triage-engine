package seemedics.dao.triageprotocol;

import seemedics.model.triage.TriageProtocol;

/**
 * @author victorp
 */
public interface TriageProtocolDao {
    void add(TriageProtocol triageProtocol);
    TriageProtocol get(String id);
}
