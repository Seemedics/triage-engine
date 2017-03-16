package seemedics.model.dialog;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import seemedics.model.Fact;

import java.util.Map;
import java.util.Set;

/**
 * @author victorp
 */

//TODO delete

@Data
@Builder
public class AnswerToFacts {

    @Singular("ans2facts")
    private Map<PredefAnswer,Set<Fact>> factsByAns;
}
