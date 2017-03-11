package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import seemedics.dao.Entity;
import seemedics.model.Fact;

import java.util.Set;

/**
 * @author victorp
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TriageProtocol extends Entity {
    /**
     * Defines the entry point of the protocol
     */

    private TriageFlow flow;

}
