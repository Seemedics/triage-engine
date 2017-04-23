package seemedics.service.meta;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import seemedics.model.metadata.MedSymptomDescriptor;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

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
    public void getSymptomDescriptorCorrectId() throws Exception {
        Optional<MedSymptomDescriptor> descriptor = localFileMetadata.getSymptomDescriptor("sym-body-temperature");
        Assert.assertThat(descriptor.get().getId(), Is.is("sym-body-temperature"));
        Assert.assertThat(descriptor.get().getName(), Is.is("Body Temperature"));
    }

    @Test
    public void getSymptomDescriptorIncorrectId() throws Exception {
        Optional<MedSymptomDescriptor> descriptor = localFileMetadata.getSymptomDescriptor("_in_correct_id");
        Assert.assertThat(descriptor.isPresent(), Is.is(false));
    }

    @Test
    public void stream() throws Exception {
        Stream<MedSymptomDescriptor> stream = localFileMetadata.stream();
        Assert.assertThat(stream.count(), Is.is(3L));
    }
}