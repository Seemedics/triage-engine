package seemedics.triage.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seemedics.model.Fact;
import seemedics.model.triage.TriageProtocol;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * This is a temp selector that pick up a protocol using naive approach: <p>
 *     A protocol will be selected  <p>
 *     If at least one fact (from the given set)  <p>
 *     has the same descriptor id as initialSymptomDescId then <p>
 *
 * This naive approach does NOT take into considerations cases when multiple
 * facts are relevant for specific protocol and also multiple protocols mat be a good
 * candidates for the given set of facts.
 *
 * <p>
 * The real selector must be rule based instead !!!
 * @author victorp
 */

@Slf4j
@Service
public class NaiveTriageProtocolSelector implements TriageProtocolSelector {

    @Autowired
    private TriageProtocols triageProtocols;


    @Override
    public Optional<TriageProtocol> select(Set<Fact> facts) {
        return triageProtocols.stream()
                .filter(protocolMatch(facts))
                .findFirst();
    }

    private static Predicate<TriageProtocol> protocolMatch(Set<Fact> facts) {
        return (protocol) -> facts.stream()
                    .anyMatch(fact -> protocolMatch(protocol,fact));

    }

    private static boolean protocolMatch(TriageProtocol protocol, Fact fact) {
        return (protocol.getInitialSymptomDescId().equals(fact.getRef()) &&
                (fact.getStatus().equals(Fact.Status.EXISTS)));
    }
}
