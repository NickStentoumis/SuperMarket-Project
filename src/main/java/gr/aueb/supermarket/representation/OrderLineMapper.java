package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.OrderLine;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class OrderLineMapper {
    public abstract OrderLineRepresentation toRepresentation(OrderLine ol);
    public abstract List<OrderLineRepresentation> toRepresentation(List<OrderLine> oll);

}
