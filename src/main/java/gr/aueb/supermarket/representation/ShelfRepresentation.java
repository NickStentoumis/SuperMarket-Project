package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ShelfRepresentation {
    public int id;
    public int ShelfLevel;
    public  int Capacity;
    public  ShelfContainerRepresentation shelfContainer;
}
