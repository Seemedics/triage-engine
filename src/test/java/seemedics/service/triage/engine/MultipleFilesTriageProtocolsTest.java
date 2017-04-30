package seemedics.service.triage.engine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import seemedics.model.triage.TriageProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by igor-z on 24-Apr-17.
 */
public class MultipleFilesTriageProtocolsTest {
    private LocalFilesTriageProtocols localFilesTriageProtocols = new LocalFilesTriageProtocols();

    private class MiltiFilesTriageProtocolsSource implements TriageProtocolsSource{
        @Override
        public Stream<InputStream> inputStreams() {
            ClassPathResource protocol01 = new ClassPathResource("seemedics/model/triage/engine/test-protocol-01.json");
            ClassPathResource protocol02 = new ClassPathResource("seemedics/model/triage/engine/test-protocol-02.json");

            try {
                return Stream.of(protocol01.getInputStream(),protocol02.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("Failed to load inputStream from classPathResources"+protocol01.getPath()+", "+protocol02.getPath(),e);
            }
        }
    }

    @Before
    public void initTest() throws IOException {
        localFilesTriageProtocols.triageProtocolsSource = new MiltiFilesTriageProtocolsSource();
        localFilesTriageProtocols.init();
    }

    @Test
    public void init() {
        Assert.assertNotNull(localFilesTriageProtocols);
    }

    @Test
    public void getCorrectIds() throws Exception {
        Optional<TriageProtocol> protool01 = localFilesTriageProtocols.get("tri-prot-test-01");
        Assert.assertThat(protool01.get().getId(), Is.is("tri-prot-test-01"));
        Optional<TriageProtocol> protool02 = localFilesTriageProtocols.get("tri-prot-test-02");
        Assert.assertThat(protool02.get().getId(), Is.is("tri-prot-test-02"));
    }

    @Test
    public void getIncorrectId() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("_incorrect_id_");
        Assert.assertThat(protool.isPresent(), Is.is(false));
    }

    @Test
    public void stream() throws Exception {
        Stream<TriageProtocol> stream = localFilesTriageProtocols.stream();
        Assert.assertThat(stream.count(), Is.is(2L));
    }

}
