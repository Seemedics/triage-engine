package seemedics.service.triage.engine;

import seemedics.model.Fact;

import java.util.Set;

/**
 * @author victorp
 */
public interface TriageEngine {


    /**
     * The engine will pick up corresponding protocol according to the given facts
     * and provide corresponding result that may be my be final or intermediate.
     *
     * @return the result from this step (my be final or intermediate)
     */
    TriageResult start(TriageStartIn triageStartIn);

    /**
     * The engine will continue the protocol flow according to the given input
     * @return the result from this step (my be final or intermediate)
     */
    TriageResult next(TriageNextIn triageNextIn);
}
