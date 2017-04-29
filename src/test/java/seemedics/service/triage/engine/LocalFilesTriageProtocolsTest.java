package seemedics.service.triage.engine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seemedics.model.triage.TriageProtocol;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by igor-z on 05-Apr-17.
 */
public class LocalFilesTriageProtocolsTest {

    private LocalFilesTriageProtocols localFilesTriageProtocols = new LocalFilesTriageProtocols();

    private class UnitTestTriageProtocolsSource implements TriageProtocolsSource{

        @Override
        public Path get_pathToProtocols() {
            return Paths.get("src/test/resources/seemedics/model/triage/examples/sore-throat-protocol-data.json");
        }
    }

    @Before
    public void initTest() throws IOException {
        localFilesTriageProtocols.triageProtocolsSource = new UnitTestTriageProtocolsSource();
        localFilesTriageProtocols.init();
    }

    @Test
    public void init() {
        Assert.assertNotNull(localFilesTriageProtocols);
    }

    @Test
    public void getCorrectId() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("tri-prot-sore-throat");
        Assert.assertThat(protool.get().getId(), Is.is("tri-prot-sore-throat"));
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