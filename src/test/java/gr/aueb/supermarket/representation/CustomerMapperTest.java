package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Customer;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerMapperTest extends IntegrationBase {

    @Inject
    private CustomerMapper customerMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void customerTorepresentation(){
        Customer customer = em.find(Customer.class, 2L);
        assertNotNull(customer);

        CustomerRepresentation customerRepresentation = customerMapper.toRepresentation(customer);
        assertNotNull(customerRepresentation);

        assertEquals("Nikolaos", customerRepresentation.firstname);

    }

    @Test
    @Transactional
    void customerRepresentationList(){
        List<Customer> customers = em.createQuery("Select c from Customer c", Customer.class).getResultList();
        assertNotNull(customers);
        assertEquals(5, customers.size());

        List<CustomerRepresentation> customerRepresentations = customerMapper.toRepresentation(customers);
        assertEquals("Nikolaos", customerRepresentations.get(1).firstname);

    }
}
