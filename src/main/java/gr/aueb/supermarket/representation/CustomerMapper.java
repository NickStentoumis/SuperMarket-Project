package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CustomerMapper {

    public abstract CustomerRepresentation toRepresentation(Customer customer);

    public abstract List<CustomerRepresentation> toRepresentation(List<Customer> customers);

    public Customer toEntity(CustomerRepresentation customerRepresentation){
        Customer customer = new Customer(customerRepresentation.firstname, customerRepresentation.lastname,
                customerRepresentation.email, customerRepresentation.phoneNumber, customerRepresentation.dateOfBirth,
                customerRepresentation.gender, customerRepresentation.userInfo);
        return customer;
    }
}
