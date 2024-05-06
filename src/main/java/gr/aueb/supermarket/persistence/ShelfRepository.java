package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Shelf;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class ShelfRepository implements PanacheRepositoryBase<Shelf, Integer> {
public List<Shelf> GetAll(){
    return listAll();
    }
}
