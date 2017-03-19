package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import seemedics.model.Entity;

/**
 * @author victorp
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Urgency extends Entity {

    protected Urgency() {
    }

    /**
     * Unique level
     * Starts from 1 when 1 is the most urgent level that means "call emergency now"
     */
    private int level;

    /**
     * Human readable interpretation (as:  "call emergency now")
     */
    private String interpretation;

    @Builder
    private Urgency(String id, String name, int level, String interpretation) {
        super(id, name);
        this.level = level;
        this.interpretation = interpretation;
    }
}
