package seemedics.model.dialog;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * @author victorp
 */

@Data
@Builder
public class PredefAnswer {
    private String id;
    private String text;
    private Optional<String> imageUrlOpt;
}
