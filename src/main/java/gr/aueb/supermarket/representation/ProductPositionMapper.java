package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.ProductPosition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProductPositionMapper {

    public abstract ProductPositionRepresentation toRepresentation(ProductPosition productPosition);

    public abstract List<ProductPositionRepresentation> toRepresentation(List<ProductPosition> productPositions);
}
