package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Customer;
import gr.aueb.supermarket.domain.Order;
import gr.aueb.supermarket.domain.OrderLine;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listCustomers(){

        Query query = em.createQuery("select c from Customer c");
        List<Customer> result = query.getResultList();
        assertEquals(5,result.size());

        Customer customer = result.get(0);
        assertNotNull(customer.getUsername());
        assertNotNull(customer.getGender());

        Customer customer5 = result.get(4);
        assertNotNull(customer5.getUsername());
        assertNotNull(customer5.getGender());

    }

    @Test
    public void getSpecificCustomer(){
        Query query = em.createQuery("select c from Customer c where c.email=:email");
        query.setParameter("email", "stentoumisn@gmail.com");
        List<Customer> customer = query.getResultList();

        assertEquals("Nikolaos", customer.get(0).getFirstname());
    }
    @Test
    public void testCustomerUpdate(){
        Query query = em.createQuery("select c from Customer c where c.email=:email");
        query.setParameter("email", "stentoumisn@gmail.com");
        List<Customer> customer = query.getResultList();

        String newEmail = "manospartsi@hotmail.com";
        customer.get(0).setEmail(newEmail);
        assertEquals(newEmail,customer.get(0).getEmail());
    }
    @Test
    public void testCustomerCount() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Customer c");
        Long count = (Long) query.getSingleResult();

        assertEquals(5L, count);
    }

    @Test
    public void getOrdersForSpecificCustomer(){
        Query query = em.createQuery("select o from Order o " +
                "join fetch o.orderingCustomer c " +
                "where c.email=:email");
        query.setParameter("email", "stentoumisn@gmail.com");
        List<Order> orders = query.getResultList();

        assertEquals(2, orders.size());
    }

    @Test
    public void findOrderLinesByCustomer() {
        Query query = em.createQuery("select ol from OrderLine ol " +
                "join fetch ol.order o " +
                "join fetch o.orderingCustomer c " +
                "where c.email = :email");
        query.setParameter("email", "partsinevelosm@gmail.com");
        List<OrderLine> orderLines = query.getResultList();
        assertEquals(7, orderLines.size());
    }

}
