package seemedics.model.triage;

import lombok.Builder;
import lombok.Data;

/**
 * @author victorp
 */

@Data
@Builder
public class Urgency {
    /**
     * Unique level
     * Starts from 1 when 1 is the most urgent level that means "call emergency now"
     */
    private int level;

    /**
     * Human readable interpretation (as:  "call emergency now")
     */
    private String interpratation;


}
