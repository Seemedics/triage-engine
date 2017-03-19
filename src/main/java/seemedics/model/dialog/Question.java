package seemedics.model.dialog;

import lombok.*;
import seemedics.model.Entity;

import java.util.Set;

/**
 * @author victorp
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Getter
public class Question extends Entity {
    protected Question() {
    }

    @Builder
    public Question(String id, String name, String text, @Singular Set<PredefAnswer> choices) {
        super(id, name);
        this.text = text;
        this.choices = choices;
    }


    @NonNull
    private String text;

    @NonNull
    private Set<PredefAnswer> choices;

}
