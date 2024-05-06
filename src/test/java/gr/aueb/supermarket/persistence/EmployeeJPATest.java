package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Employee;
import gr.aueb.supermarket.domain.Order;
import gr.aueb.supermarket.domain.OrderLine;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class EmployeeJPATest {

    @Inject
    EntityManager em;

    @Test
    public void listEmployees(){

        Query query = em.createQuery("select e from Employee e");
        List<Employee> result = query.getResultList();

        assertEquals(3,result.size());
        assertEquals("Nick", result.get(0).getFirstname());
    }

    @Test
    public void getSpecificEmployee(){

        Query query = em.createQuery("select e from Employee e where e.position=:position");
        query.setParameter("position", "Director");
        List<Employee> result = query.getResultList();

        assertEquals(1,result.size());
        assertEquals("George", result.get(0).getFirstname());
    }

    @Test
    public void getOrdersProductsForEmployee(){

        Query query = em.createQuery("select o from Order o " +
                "join fetch o.employee e " +
                "where e.position=:position");
        query.setParameter("position", "Director");
        List<Order> result = query.getResultList();

        assertEquals(4,result.size());
        assertNotNull(result.get(0).getOrderlines());
        assertEquals(2,result.get(0).getOrderlines().size());

        List<OrderLine> orderLines = new ArrayList<OrderLine>(result.get(0).getOrderlines());
        OrderLine ol = orderLines.get(1);
        assertNotNull(ol);
    }

    @Test
    public void fetchOrderWithPickup(){

        Query query = em.createQuery("select o from Order o " +
                "join fetch o.employee e " +
                "join fetch o.orderingCustomer c " +
                "join fetch o.pickup p " +
                "where e.position=:position");
        query.setParameter("position", "Director");
        List<Order> result = query.getResultList();

        assertNotNull(result.get(0).getPickup());
    }
}
