package seemedics.service.meta;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by igor-z on 01-Apr-17.
 */
//@RunWith(SpringRunner.class)
public class LocalFileMetadataTest {
    @Autowired
    private LocalFileMetadata localFileMetadata;

    @Test
    public void init() throws Exception {
        //LocalFileMetadata localFileMetadata = new LocalFileMetadata();
        //Assert.assertTrue(true);
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