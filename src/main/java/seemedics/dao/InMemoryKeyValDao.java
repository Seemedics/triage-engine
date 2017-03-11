package seemedics.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author victorp
 */
public class InMemoryKeyValDao<T extends Entity> {
    private Map<String,T> entities = new HashMap<>();

    public void add(T entity){
        entities.put(entity.getId(),entity);
    }

    public T get(String id){
        return entities.get(id);
    }

}
