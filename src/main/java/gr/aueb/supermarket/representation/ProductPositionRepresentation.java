package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductPositionRepresentation {

    public int quantity;
    public int shelfID;
}
