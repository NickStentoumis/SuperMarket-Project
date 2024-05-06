package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Customer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    public void getCustomerbyId(){
        Customer customer = customerRepository.findById(2);
        assertEquals("Nikolaos", customer.getFirstname());
    }
}
