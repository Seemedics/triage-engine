package seemedics.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author victorp
 */
public class CollectionUtil {

    public static <K,V>  HashMap<K,V> toHashMap(Map<K,V> map){
        HashMap<K,V> result = new HashMap<>(map.size());
        result.putAll(map);
        return result;
    }
}
