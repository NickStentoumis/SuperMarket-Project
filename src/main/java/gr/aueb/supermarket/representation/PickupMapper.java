package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Pickup;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PickupMapper {

    public abstract PickupRepresentation toRepresentation(Pickup pickup);

    public abstract List<PickupRepresentation> toRepresentation(List<Pickup> pickups);
}
