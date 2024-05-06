package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Order;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepository;

    @Test
    public void listOrders(){

        List<Order> orders = orderRepository.listAll();
        assertEquals(10, orders.size());
    }

    @Test
    public void getToBeExecutedOrders(){
        List<Order> orders = orderRepository.getOrdersToBeExecuted();

        assertEquals(1, orders.size());
    }

    @Test
    public void findOrdersForSpecificCustomer(){
        List<Order> orders = orderRepository.findOrdersByCustomerName("Papadopoulos");

        assertNotNull(orders);
    }

    @Test
    public void findOrdersForSpecificEmployee(){
        List<Order> orders = orderRepository.findOrdersByEmployeeName("Sloukas");

        assertNotNull(orders);
    }

    @Test
    public void fetchOrderWithOrderingCustomer(){


        List<Order> orders = orderRepository.find("select o from Order o join o.orderingCustomer c where c.userInfo.username= ?1", "nck").list();

        assertEquals(10,orders.size());


        Order theOrder = orders.get(0);
        assertNotNull(theOrder.getOrderingCustomer());
        assertEquals("nck",theOrder.getOrderingCustomer().getUsername());
    }

    @Test
    public void fetchOrderWithOrderLine(){

        List<Order> orders = orderRepository.find("select o from Order o join o.orderlines ol join ol.product p where ol.quantity = 10").list();

        assertEquals("Apples", orders.get(0).getOrderlines().get(0).getProduct().getName());
    }
}
