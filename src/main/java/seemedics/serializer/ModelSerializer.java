package seemedics.serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author victorp
 */
public class ModelSerializer {
    public static final ObjectMapper mapper = initMapper();

    private static ObjectMapper initMapper(){
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        newMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return newMapper;
    }

}
