package seemedics.model.selfcare;

import lombok.Data;

import java.util.Set;

/**
 * @author victorp
 */

@Data
public class SelfCareRecommendation {
    String id;
    Set<Product> products;
}
