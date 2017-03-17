package seemedics.model.dialog;

import lombok.*;
import seemedics.dao.Entity;
import seemedics.model.Fact;

import java.util.Map;
import java.util.Set;

/**
 * @author victorp
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class Question extends Entity {
    @Builder
    public Question(String id, String name, String text, @Singular Set<PredefAnswer> choices) {
        super(id, name);
        this.text = text;
        this.choices = choices;
    }


    @NonNull
    private final String text;

    @NonNull
    private final Set<PredefAnswer> choices;

}
