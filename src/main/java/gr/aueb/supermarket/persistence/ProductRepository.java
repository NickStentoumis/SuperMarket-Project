package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Integer> {

    public List<Product> searchByName(String name){
        if (name == null){
            return listAll();
        }

        return find("name", name).list();
    }
}
