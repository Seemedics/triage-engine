package seemedics.model;

import java.util.HashSet;
import java.util.Set;

import lombok.*;
import org.assertj.core.util.Sets;

/**
 * @author victorp
 */

@Data
@Setter(AccessLevel.PRIVATE)
@Getter
public class Facts {
    private Facts() {
    }

    @Builder
    public Facts(@Singular Set<Fact> facts) {
        this.facts = Sets.newHashSet(facts);
    }

    public HashSet<Fact> facts;
}
