package gr.aueb.supermarket.persistence;


import gr.aueb.supermarket.domain.Order;
import gr.aueb.supermarket.domain.Pickup;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PickupJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listPickUps() {

        Query query = em.createQuery("select p from Pickup p");
        List<Pickup> result = query.getResultList();

        assertEquals(10, result.size());
    }

    @Test
    public void findOrderFromPickup() {

        Query query = em.createQuery("select o from Pickup p join p.order o");
        List<Order> result = query.getResultList();

        assertEquals(19, result.get(9).getOrderCost());
    }

    @Test
    public void countOrdersAndPickUps(){

        Query query1 = em.createQuery("select count(p) from Pickup p ");
        Query query2 = em.createQuery("select count(o) from Order o");

        List<Pickup> result1 = query1.getResultList();
        List<Order> result2 = query2.getResultList();

        assertEquals(result1.size(),result2.size());
    }

}
