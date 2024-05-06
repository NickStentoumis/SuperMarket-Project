package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class EmployeeRepository implements PanacheRepositoryBase<Employee, Integer> {

    public List<Employee> findByLastName(String lastName){
        return find("lastname", lastName).list();
    }
}
