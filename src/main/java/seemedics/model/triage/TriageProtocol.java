package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import seemedics.model.Fact;

import java.util.Set;

/**
 * @author victorp
 */
@Data
@Builder
public class TriageProtocol {

    /**
     * Defines the entry point of the protocol
     */
    @Singular
    private Set<Fact> initialFacts;

    private TriageFlow flow;

}
