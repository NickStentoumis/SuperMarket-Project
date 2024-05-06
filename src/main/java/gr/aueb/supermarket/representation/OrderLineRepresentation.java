package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class OrderLineRepresentation {
    public int id;
    public int productId;
    public int quantity;
}
