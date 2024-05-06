package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.common.Category;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductRepresentation {

    public int id;
    public String name;
    public double price;
    public Category category;
    public ProductPositionRepresentation productPosition;

}
