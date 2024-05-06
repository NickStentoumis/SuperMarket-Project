package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Order;
import gr.aueb.supermarket.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listOrders () {

        Query query = em.createQuery("select o from Order o");
        List<Order> result = query.getResultList();

        assertEquals(10, result.size());

        Order o = result.get(0);
        assertNotNull(o);
        assertNotNull(o.getOrderingCustomer());
    }

    @Test
    public void fetchOrderWithOrderingCustomer(){


        Query query = em.createQuery("select o from Order o " + "join fetch o.orderingCustomer c" +
                                        " where c.userInfo.username= :username");

        query.setParameter("username", "nck");

        List<Order> result = query.getResultList();
        assertEquals(10,result.size());


        Order theOrder = result.get(0);
        assertNotNull(theOrder.getOrderingCustomer());
        assertEquals("nck",theOrder.getOrderingCustomer().getUsername());
    }

    @Test
    public void fetchOrderWithOrderLine(){

        Query query = em.createQuery("select p from Order o join o.orderlines ol " +
                                        "join ol.product p " +
                                        "where ol.quantity = 10");

        List<Product> results = query.getResultList();
        assertEquals("Lays", results.get(1).getName());
    }

    @Test
    public void persistOrderWithEmployeeCustomer(){

        String customerEmail = "stentoumisn@gmail.com";
        String employeeEmail = "gstm@gmail.com";

        Order order = fetchOrderWithEmployeeWithCustomer(customerEmail, employeeEmail);

        assertNotNull(order.getPickup());

    }


    public Order fetchOrderWithEmployeeWithCustomer(String customerEmail, String employeeEmail){

        Query query = em.createQuery("select o from Order o " +
                "join fetch o.orderingCustomer c " +
                "join fetch o.employee e " +
                "where e.email = :employeeEmail and c.email = :customerEmail");
        query.setParameter("employeeEmail", employeeEmail);
        query.setParameter("customerEmail", customerEmail);

        List<Order> orders = query.getResultList();
        assertEquals(1, orders.size());

        return orders.get(0);
    }
}
