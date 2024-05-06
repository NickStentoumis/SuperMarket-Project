package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Shelf;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = ShelfContainerMapper.class)
public abstract class ShelfMapper {
    public abstract ShelfRepresentation toRepresentation(Shelf shelf);
    public abstract List<ShelfRepresentation> toRepresentation(List<Shelf> shelf);

    public Shelf toEntity(ShelfRepresentation shelfRepresentation){
        Shelf shelf = new Shelf(shelfRepresentation.ShelfLevel, shelfRepresentation.Capacity);
        return shelf;
    }
}
