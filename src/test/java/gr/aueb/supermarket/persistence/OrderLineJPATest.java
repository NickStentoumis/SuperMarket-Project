package gr.aueb.supermarket.persistence;

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
public class OrderLineJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listOrderLine () {

        Query query = em.createQuery("select ol from OrderLine ol");
        List<OrderLine> result = query.getResultList();

        assertEquals(18, result.size());

        OrderLine orderline = result.get(0);
        assertNotNull(orderline.getOrder());
        assertNotNull(orderline.getQuantity());
    }

    @Test
    public void orderLineQuantity() {

        Query query = em.createQuery("select ol from OrderLine ol where ol.quantity >= 10");

        List<OrderLine> result = query.getResultList();
        assertEquals(6, result.size());
    }

    @Test
    public void fetchOrderLineWithProduct() {

        Query query = em.createQuery("select ol from OrderLine ol join fetch ol.product p where p.name =:name");
        query.setParameter("name", "Lays");

        List<OrderLine> result = query.getResultList();
        assertEquals(1, result.size());
    }

    @Test void fetchOrderLineWithProductWithShelf(){
        Query query = em.createQuery("select ol from OrderLine ol join fetch ol.product p " +
                "join fetch p.productPosition pp " +
                "join fetch pp.shelf " +
                "where p.name =: name");
        query.setParameter("name", "Cheetos");

        List<OrderLine> result = query.getResultList();
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getProduct().getProductPosition().GetShelf().GetShelfLevel());
        assertEquals(100, result.get(0).getProduct().getProductPosition().GetShelf().GetMaxCapacity());
    }

    @Test void fetchOrderLineWithProductWithShelfWithShelfContainer(){
        Query query = em.createQuery("select ol from OrderLine ol join fetch ol.product p " +
                "join fetch p.productPosition pp " +
                "join fetch pp.shelf s " +
                "join fetch s.shelfContainer " +
                "where p.name =: name");
        query.setParameter("name", "Molto");

        List<OrderLine> result = query.getResultList();
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).getProduct().getProductPosition().GetShelf().GetShelfLevel());
        assertEquals(100, result.get(0).getProduct().getProductPosition().GetShelf().GetMaxCapacity());
        assertNotNull(result.get(0).getProduct().getProductPosition().GetShelf().getShelfContainer());
        assertEquals(1, result.get(0).getProduct().getProductPosition().GetShelf().getShelfContainer().getCorridor());
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
