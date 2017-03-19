package seemedics.model;

import lombok.*;

/**
 * @author victorp
 */

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Entity {
    @NonNull
    private String id;

    @NonNull
    private String name;

    protected Entity() {
    }
}
