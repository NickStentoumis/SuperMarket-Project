package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Order;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderMapperTest {

        @Inject
        private OrderMapper orderMapper;

        @Inject
        private EntityManager em;

        @Test
        @Transactional
        void orderToRepresentation(){
            gr.aueb.supermarket.domain.Order order = em.find(Order.class, 2L);
            assertNotNull(order);

            OrderRepresentation orderRepresentation = orderMapper.toRepresentation(order);
            assertNotNull(orderRepresentation);

            LocalDate date = LocalDate.of(1990, 1, 1);
            //assertEquals(date, orderRepresentation.orderDate);

        }

        @Test
        @Transactional
        void orderRepresentationList(){
            List<Order> orders = em.createQuery("Select o from Order o", Order.class).getResultList();
            assertNotNull(orders);
            assertEquals(10, orders.size());

            List<OrderRepresentation> orderRepresentations = orderMapper.toRepresentation(orders);
            LocalDate date = LocalDate.of(1990, 1, 1);
            assertEquals("1990-01-01", orderRepresentations.get(1).orderDate);

        }
    }
