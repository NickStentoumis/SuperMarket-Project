package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
injectionStrategy = InjectionStrategy.CONSTRUCTOR,
uses = {ProductPositionRepresentation.class})
public abstract class ProductMapper {
    public abstract ProductRepresentation toRepresentation(Product product);

    public abstract List<ProductRepresentation> toRepresentation(List<Product> product);

    public Product toEntity(ProductRepresentation productRepresentation){
        Product product = new Product(productRepresentation.price, productRepresentation.name, productRepresentation.category);
        return product;
    }

 //   public abstract Product toEntity(ProductRepresentation productRepresentation);
}
