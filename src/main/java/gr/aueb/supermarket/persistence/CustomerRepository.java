package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, Integer> {

    public List<Customer> findByLastName(String lastName){
        return find("lastname", lastName).list();
    }
}
