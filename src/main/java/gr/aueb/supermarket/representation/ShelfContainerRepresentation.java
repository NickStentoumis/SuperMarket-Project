package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.HashSet;
import java.util.Set;

@RegisterForReflection
public class ShelfContainerRepresentation {
    public  Long id;
    public  int Floor;
    public  int Corridor;

    public Set<ShelfRepresentation> shelves = new HashSet<ShelfRepresentation>();
}
