package seemedics.service.triage.engine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import seemedics.model.triage.TriageProtocol;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by igor-z on 05-Apr-17.
 */
public class LocalFilesTriageProtocolsTest {

    private LocalFilesTriageProtocols localFilesTriageProtocols = new LocalFilesTriageProtocols();

    @Before
    public void initTest() throws IOException {
        localFilesTriageProtocols.metadataResource = new ClassPathResource("seemedics/model/triage/examples/sore-throat-protocol-data.json");
        localFilesTriageProtocols.init();
    }

    @Test
    public void init() {
        Assert.assertNotNull(localFilesTriageProtocols);
    }

    @Test
    public void get_correct_id() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("tri-prot-sore-throat");
        Assert.assertThat(protool.get().getId(), Is.is("tri-prot-sore-throat"));
    }

    @Test
    public void get_incorrect_id() throws Exception {
        Optional<TriageProtocol> protool = localFilesTriageProtocols.get("_incorrect_id_");
        Assert.assertThat(protool.isPresent(), Is.is(false));
    }

    @Test
    public void stream() throws Exception {
    }


}