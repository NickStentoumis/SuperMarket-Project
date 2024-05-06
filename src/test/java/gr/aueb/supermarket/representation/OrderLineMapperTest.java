package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.OrderLine;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderLineMapperTest {

    @Inject
    private OrderLineMapper orderLineMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void orderLineToRepresentation(){
        OrderLine orderLine = em.find(OrderLine.class, 2L);
        assertNotNull(orderLine);

        OrderLineRepresentation orderLineRepresentation = orderLineMapper.toRepresentation(orderLine);
        assertNotNull(orderLineRepresentation);

        assertEquals(4, orderLineRepresentation.quantity);

    }

    @Test
    @Transactional
    void orderLineRepresentationList(){
        List<OrderLine> orderLines = em.createQuery("Select o from OrderLine o", OrderLine.class).getResultList();
        assertNotNull(orderLines);
        assertEquals(18, orderLines.size());

        List<OrderLineRepresentation> orderLineRepresentations = orderLineMapper.toRepresentation(orderLines);
        assertEquals(3, orderLineRepresentations.get(1).quantity);

    }
}
