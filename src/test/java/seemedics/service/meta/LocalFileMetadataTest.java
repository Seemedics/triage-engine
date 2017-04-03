package seemedics.service.meta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by igor-z on 01-Apr-17.
 */

public class LocalFileMetadataTest {

    private LocalFileMetadata localFileMetadata = new LocalFileMetadata();


   @Before
   public void initTest() throws IOException {
       localFileMetadata.metadataResource = new ClassPathResource("seemedics/model/triage/examples/symptoms-descriptors.json");
       localFileMetadata.init();
   }


    @Test
    public void init() throws Exception {
        Assert.assertNotNull(localFileMetadata);
    }

    @Test
    public void getSymptomDescriptor() throws Exception {
        Assert.fail();
    }

    @Test
    public void stream() throws Exception {
        Assert.fail();
    }

}