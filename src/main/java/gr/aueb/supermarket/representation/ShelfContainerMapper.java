package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.ShelfContainer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;



@Mapper(componentModel = "jakarta", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ShelfContainerMapper {
    public abstract ShelfContainerRepresentation toRepresentation(ShelfContainer shelfContainer);
    public abstract List<ShelfContainerRepresentation> toRepresentation(List<ShelfContainer> shelves);

    public ShelfContainer toEntity(ShelfContainerRepresentation shelfContainerRepresentation){
        ShelfContainer shelfContainer = new ShelfContainer(shelfContainerRepresentation.Floor, shelfContainerRepresentation.Corridor);
        return shelfContainer;
    }
}
