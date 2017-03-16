package seemedics.model;

import lombok.*;
import seemedics.dao.Entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * Any fact about a patient as symptom, test result, age ect
 *
 * @author victorp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class Fact extends Entity{

    @Builder
    public Fact(String id, String name, String ref, String status, Optional<Long> timePeriodMillis, @Singular Map<String, String> values) {
        super(id, name);
        this.ref = ref;
        this.status = status;
        this.timePeriodMillis = timePeriodMillis;
        this.values = values;
    }

    /**
     * Reference to unique term as: <p>
     * 'headache' (which is as symptom) <p>
     * 'cancer' (which is condition) <p>
     * 'age' which is human attribute <p>
     */
    public final String ref;



    /**
     * Valid options:
     * EXISTS
     * NOT_EXISTS
     * UNKNOWN
     *
     * Indicates whether the fact presents, does NOT present or unknown
     * Examples:
     * There is NO headache (the fact is: 'Headache does NOT present') - NOT_EXISTS
     * Body temperature 36.6. (the fact is: 'Body temperature is 36.6') - EXISTS
     * The user doesn't know his blood pressure (the fact is: "Blood pressure is unknown) - UNKNOWN
     */
    public final String status;


    /**
     * How long this fact presents. <p>
     * Non empty only if presents=true <p>
     * Empty means unknown <p>
     */
    public final Optional<Long> timePeriodMillis;

    /**
     * values must be empty if presents=false <p>
     *
     * <p>
     * Additional values of the symptom <p>
     *     Examples:<p>
     *       Symptom is fever; values: temperature -> 39 <p>
     *       Symptom is headache; values: pain severity -> moderate <p>
     */

    public final Map<String,String> values;

    public Set<Fact> asSet(){
        Set<Fact> result = new HashSet<>();
        result.add(this);
        return result;
    }

    static public class Status{
        static public final String NOT_EXISTS = "NOT_EXISTS";
        static public final String EXISTS = "EXISTS";
        static public final String UNKNOWN = "UNKNOWN";


    }
}
