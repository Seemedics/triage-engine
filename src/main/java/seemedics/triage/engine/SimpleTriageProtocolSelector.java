package seemedics.triage.engine;

import org.springframework.beans.factory.annotation.Autowired;
import seemedics.model.Fact;
import seemedics.model.triage.TriageProtocol;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * This is a temp selector that pick up a protocol using naive approach:
 *
 * The real selector must be rule based instead.
 * @author victorp
 */
public class SimpleTriageProtocolSelector implements TriageProtocolSelector {

    @Autowired
    private TriageProtocolsHolder triageProtocols;


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
