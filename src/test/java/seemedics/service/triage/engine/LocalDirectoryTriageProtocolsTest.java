package seemedics.service.triage.engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by igor-z on 24-Apr-17.
 */
public class LocalDirectoryTriageProtocolsTest {
    private LocalFilesTriageProtocols localFilesTriageProtocols = new LocalFilesTriageProtocols();

    @Before
    public void initTest() throws IOException {
        //TODO
      Path resourceDirectory = Paths.get("seemedics\\model\\triage\\protocolsDirectory");
        localFilesTriageProtocols.metadataResource = new ClassPathResource("seemedics\\model\\triage\\protocolsDirectory");
        localFilesTriageProtocols.init();
    }

    @Test
    public void init() {
        Assert.assertNotNull(localFilesTriageProtocols);
    }

}
