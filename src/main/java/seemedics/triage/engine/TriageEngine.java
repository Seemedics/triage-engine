package seemedics.triage.engine;

import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;

import java.util.Set;

/**
 * @author victorp
 */
public interface TriageEngine {


    /**
     * The engine will pick up corresponding protocol according to the given facts
     * and provide corresponding result that may be my be final or intermediate.
     *
     * @param facts all known facts about corresponding user
     * @return the result from this step (my be final or intermediate)
     */
    TriageResult start(Set<Fact> facts);

    /**
     * The engine will continue the protocol flow according to the given protocolId and stepId
     *
     * @param protocolId from the previous result
     * @param stepId from the previous result
     * @param facts all known facts about corresponding user
     * @param answer the answer to the question from the previous result
     * @return the result from this step (my be final or intermediate)
     */
    TriageResult next(String protocolId,String stepId,Set<Fact> facts, PredefAnswer answer);

}
