package seemedics.model.metadata;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by igor-z on 01-Apr-17.
 */
public class HumanAttributeDescriptorTest {
    @Test
    public void descriptorConstructor() throws Exception {
        HumanAttributeDescriptor descriptor = new HumanAttributeDescriptor("id", "name");
        Assert.assertThat(descriptor.getId(), Is.is("id"));
        Assert.assertThat(descriptor.getName(), Is.is("name"));
        Assert.assertThat(descriptor.toString(), Is.is("HumanAttributeDescriptor()"));
    }

//    @Test
//    public void equals() throws Exception {
//    }
//
//    @Test
//    public void hashCode() throws Exception {
//    }
//
    @Test
    public void same_canEqual_true() throws Exception {
        HumanAttributeDescriptor descriptor1 = new HumanAttributeDescriptor("id", "name");
        HumanAttributeDescriptor descriptor2 = new HumanAttributeDescriptor("id", "name");
        boolean equals = descriptor1.equals(descriptor2);
        Assert.assertThat(equals, Is.is(true));
    }
    @Test
    public void different_canEqual_false() throws Exception {
        HumanAttributeDescriptor descriptor1 = new HumanAttributeDescriptor("id1", "name");
        HumanAttributeDescriptor descriptor2 = new HumanAttributeDescriptor("id2", "name");
        boolean equals = descriptor1.equals(descriptor2);
        Assert.assertThat(equals, Is.is(false));

        descriptor1 = new HumanAttributeDescriptor("id", "name1");
        descriptor2 = new HumanAttributeDescriptor("id", "name2");
        equals = descriptor1.equals(descriptor2);
        Assert.assertThat(equals, Is.is(false));
    }

}