package seemedics.model.dialog;

import lombok.*;
import seemedics.model.Entity;

import java.util.Optional;

/**
 * @author victorp
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class PredefAnswer extends Entity {
    protected PredefAnswer() {
    }

    @Builder
    public PredefAnswer(String id, String name, String text, String imageUrl) {
        super(id, name);
        this.text = text;
        this.imageUrl = imageUrl;
    }

    @NonNull
    private String text;


    @Getter(AccessLevel.PRIVATE)
    private String imageUrl;

    public Optional<String> getImageUrlOpt(){
        return Optional.ofNullable(imageUrl);
    }
}
