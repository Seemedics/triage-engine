package seemedics.service.triage.engine;

import java.util.HashSet;
import java.util.Set;

import lombok.*;
import org.assertj.core.util.Sets;
import seemedics.model.Fact;

/**
 * @author victorp
 */

@Data
@Setter(AccessLevel.PRIVATE)
@Getter
public class TriageStartIn {
    private TriageStartIn() {
    }

    /**
     * @param facts the initial facts (normally symptoms, age etc)
     */
    @Builder
    public TriageStartIn(@Singular Set<Fact> facts) {
        this.facts = Sets.newHashSet(facts);
    }

    private HashSet<Fact> facts;
}
