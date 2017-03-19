package seemedics.model.triage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import seemedics.model.Fact;
import seemedics.model.dialog.PredefAnswer;
import seemedics.model.dialog.Question;
import seemedics.service.meta.LocalFileMetadata;
import seemedics.model.triage.examples.SoreThroatProtocotData;
import seemedics.service.meta.LocalFileMetadata.JsonSerializableMetadata;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * @author victorp
 */

@Slf4j
public class TriageModelTest {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Test
    public void triageDataUsage() throws IOException {


        TriageProtocol soreThroatProtocol = SoreThroatProtocotData.protocol();


        printAsJson(soreThroatProtocol,SoreThroatProtocotData.metadata());



        /**
         * Now we are going to simulate the triage engine
         */

        Fact initialFact = SoreThroatProtocotData.initialFact();

        Set<Fact> facts = new HashSet<>();
        facts.add(initialFact);

        log.info("Facts so far: {}",facts);

        ConditionalFlow flow1 = soreThroatProtocol.getFlow().toConditionalFlow();

        Question q1 = flow1.toConditionalFlow().getQuestion();

        Object[] options = q1.getChoices().toArray();
        log.info("Q: {}: {}", q1.getText(), q1.getChoices().stream().map(PredefAnswer::getText).toArray());

        PredefAnswer answer = (PredefAnswer) options[1];
        log.info("A: {}", answer.getText());

        facts.add(soreThroatProtocol.factFromAnswer(answer.getId()).get());
        log.info("Facts so far: {}",facts);

    }

    private void printAsJson(TriageProtocol soreThroatProtocol, JsonSerializableMetadata metadata) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        /**
         * Protocols
         */
        String protocolsAsJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(soreThroatProtocol);
        log.info("protocols json: {}", protocolsAsJson);

        TriageProtocol protocols =  mapper.readValue(protocolsAsJson, TriageProtocol.class);
        log.info("protocols: {}",protocols.toString());

        /**
         * Metadata (Symptoms descriptors)
         */
        String metadataAsJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metadata);
        log.info("symptom descriptors json: {}",metadataAsJson);

        JsonSerializableMetadata metadataFromJson =  mapper.readValue(metadataAsJson, metadata.getClass());
        log.info("metadata: {}",metadataFromJson.toString());

    }
}
