package seemedics.model.dialog;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import seemedics.dao.Entity;

import java.util.Optional;

/**
 * @author victorp
 */

@Data
public class PredefAnswer extends Entity {
    @Builder
    public PredefAnswer(String id, String name, String text, Optional<String> imageUrlOpt) {
        super(id, name);
        this.text = text;
        this.imageUrlOpt = imageUrlOpt;
    }

    @NonNull
    private String text;

    @NonNull
    private Optional<String> imageUrlOpt;
}
