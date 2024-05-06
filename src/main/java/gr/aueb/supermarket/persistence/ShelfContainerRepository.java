package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.ShelfContainer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;


@RequestScoped
public class ShelfContainerRepository implements PanacheRepositoryBase<ShelfContainer, Integer> {
    public List<ShelfContainer> GetAll(){
        return listAll();

}
}
