package seemedics.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * @author victorp
 */

@Data
@AllArgsConstructor
public class Entity {
    @NonNull
    private final String id;

    @NonNull
    private final String name;
}
