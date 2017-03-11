package seemedics.model.selfcare;

import lombok.Data;

/**
 * @author victorp
 */

@Data
public class Product {

    /**
     * DB id
     */
    String id;

    /**
     * Universal Product Code
     */
    long upc;

    /**
     * Readable name
     */
    String name;

    /**
     * The image of the product
     */
    String imageUrl;
}
