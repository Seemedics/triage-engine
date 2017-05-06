package seemedics.util;

import org.assertj.core.util.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
