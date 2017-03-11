package seemedics.model.dialog;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Set;

/**
 * @author victorp
 */

@Data
@Builder
public class Question {
    private String id;
    private String text;

    @Singular
    private Set<PredefAnswer> choices;
}
