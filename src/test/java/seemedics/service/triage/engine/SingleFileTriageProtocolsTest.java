package seemedics.service.triage.engine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import seemedics.model.triage.TriageProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by igor-z on 05-Apr-17.
 */
public class SingleFileTriageProtocolsTest {

    private LocalFilesTriageProtocols localFilesTriageProtocols = new LocalFilesTriageProtocols();

    private class SingleFileTriageProtocolsSource implements TriageProtocolsSource{
        @Override
        public Stream<InputStream> inputStreams() {
            ClassPathResource classPathResource = new ClassPathResource("seemedics/model/triage/engine/test-protocol-01.json");
            try {
                return Stream.of(classPathResource.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("Failed to load inputStream from classPathResource"+classPathResource.getPath(),e);
            }
        }
    }

    @Before
    public void initTest() throws IOException {
        localFilesTriageProtocols.triageProtocolsSource = new SingleFileTriageProtocolsSource();
        localFilesTriageProtocols.init();
    }

    @Test
    public void init() {
        Assert.assertNotNull(localFilesTriageProtocols);
    }

    @Test
    public void getCorrectId() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("tri-prot-test-01");
        Assert.assertThat(protool.get().getId(), Is.is("tri-prot-test-01"));
    }

    @Test
    public void getIncorrectId() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("_incorrect_id_");
        Assert.assertThat(protool.isPresent(), Is.is(false));
    }

    @Test
    public void stream() throws Exception {
        Stream<TriageProtocol> stream = localFilesTriageProtocols.stream();
        Assert.assertThat(stream.count(), Is.is(1L));
    }
}