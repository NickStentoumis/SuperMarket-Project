package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    Order order;
    Order order2;
    ShelfContainer SC1;
    ShelfContainer SC2;
    Shelf S1;
    Shelf S2;
    Customer customer;
    Employee employee;
    Product P1;
    Product P2;
    ProductPosition PP1;
    ProductPosition PP2;
    OrderLine OL1;
    OrderLine OL2;
    LocalDate today = LocalDate.now();

    Pickup pickup;

    @BeforeEach
    public void setup(){

        //Address And Dates
        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        Address address = new Address("Evrou", 5, "Megara","19100" );
        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(15);
        LocalDateTime now = LocalDateTime.now();
        LocalDate orderDate = LocalDate.now();
        UserInfo userInfo = new UserInfo("nck", "0000");

        // Shelves
        SC1 = new ShelfContainer(1,2);
        SC2 = new ShelfContainer(2,2);
        S1 = SC1.addShelf(1, 10);
        S2 = SC2.addShelf(2, 1000);

        //Products
        P1 = new Product(1.2, "milk", Category.MILK);
        PP1 = P1.setProductPosition(20, S2);
        P2 = new Product(1.5, "chocolate", Category.SNACKS);
        PP2 = P2.setProductPosition(2, S1);

        //Customer
        customer = new Customer("Manolis","Partsinevelos","partsinevelosm@gmail.com","6978600994",birthDate, Gender.MALE, userInfo);

        //Order
        order = new Order(orderDate,orderDateWindow, customer);
        order2 = new Order(orderDate,orderDateWindow, customer);
        OL1 = order.addOrderLine(P1, 10);
        OL2 = order.addOrderLine(P2, 1);

        //Pickup
        order.setPickup(orderDateWindow.plusMinutes(10), PaymentMethod.CARD);

        //Employee
        employee = new Employee("Nick", "Stentoumis", "nstm@yahoo.com", "6982948593", birthDate, Gender.MALE, address, "Sales", "Employee", today, userInfo);
        order.setEmployee(employee);
    }

    @Test
    public void GettersSetters(){

        assertNotNull(order.getOrderDate());
        LocalDate now = LocalDate.now();
        order.setOrderDate(now);
        assertNotNull(order.getOrderStartWindow());
        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(20);
        order.setOrderStartWindow(orderDateWindow);
        assertNotNull(order.getOrderStopWindow());
        assertEquals(OrderStatus.TO_BE_EXECUTED, order.getOrderStatus());
        order.setOrderStatus(OrderStatus.CONCLUDED);
        assertEquals(OrderStatus.CONCLUDED, order.getOrderStatus());
        order.setOrderStatus(OrderStatus.TO_BE_EXECUTED);
        assertEquals(13.5, order.getOrderCost());
        assertNotNull(order.getOrderingCustomer());
        order.setOrderingCustomer(customer);
        assertEquals(2, order.getOrderlines().size());
        assertNotNull(order.getEmployee());
        assertEquals(1, order.getOrderPoints());
        assertNotNull(order.getPickup());
        order.getId();
        order.setOrderPoints(1);

    }

    // Order cannot contain the same orderLine twice
    @Test
    public void denySameProductMoreThanOnce(){
        DomainException exception = assertThrows(DomainException.class, () ->{ order.addOrderLine(P1, 10);});
        assertEquals("OrderLine already exists for this order", exception.getMessage());
    }

    @Test
    public void checkOrderCost(){

        assertEquals(13.5, order.getOrderCost());
    }

    @Test
    public void checkOrderPoints(){

        assertEquals(1, order.getOrderPoints());
    }

    @Test
    public void calculateDiscount(){

        assertEquals(0.02, order.calculateDiscount());

        Product P1 = new Product(100, "chocolate", Category.MILK);
        ProductPosition PP1 = P1.setProductPosition(20, S2);
        OrderLine OL1 = order.addOrderLine(P1, 10);
        assertEquals(2.02, order.calculateDiscount());

        Product P2 = new Product(550, "meat", Category.MILK);
        ProductPosition PP2 = P2.setProductPosition(20, S2);
        OrderLine OL2 = order.addOrderLine(P2, 10);
        assertEquals(19.529999999999998, order.calculateDiscount());

        Product P3 = new Product(10000, "milk", Category.MILK);
        ProductPosition PP3 = P3.setProductPosition(20, S2);
        OrderLine OL3 = order.addOrderLine(P3, 10);
        assertEquals(426.04, order.calculateDiscount());
    }

    @Test
    public void changeCustomerTier(){

        assertEquals(CustomerTier.BRONZE, customer.getTier());

        Product P1 = new Product(1148, "chocolate", Category.MILK);
        ProductPosition PP1 = P1.setProductPosition(20, S2);
        OrderLine OL1 = order.addOrderLine(P1, 1);
        assertEquals(CustomerTier.SILVER, customer.getTier());

        Product P2 = new Product(1148, "meat", Category.MILK);
        ProductPosition PP2 = P2.setProductPosition(20, S2);
        OrderLine OL2 = order.addOrderLine(P2, 5);
        assertEquals(CustomerTier.GOLD, customer.getTier());

        Product P3 = new Product(1148, "milk", Category.MILK);
        ProductPosition PP3 = P3.setProductPosition(20, S2);
        OrderLine OL3 = order.addOrderLine(P3, 10);
        assertEquals(CustomerTier.PLATINUM, customer.getTier());

    }

    // Only one employee can execute the order
    @Test
    public void denyOrderAssignmentToMultipleEmployees(){

        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        Address address = new Address("Evrou", 5, "Megara","19100" );
        UserInfo userInfo = new UserInfo("nck", "0000");
        Employee employee = new Employee("Nikolaos","Stentoumis","stentoumis@gmail.com","6971313131",birthDate, Gender.MALE, address, "Sales", "Employee", today, userInfo);

        DomainException exception = assertThrows(DomainException.class, () ->{order.setEmployee(employee);});
        assertEquals("Order Already Assigned To Another Employee", exception.getMessage());
    }

    @Test
    public void changeOrderStatus(){

        LocalDateTime now = LocalDateTime.now();
        order.setOrderStartWindow(now.plusMinutes(15));

        assertEquals(OrderStatus.TO_BE_EXECUTED, order.getOrderStatus());
    }

    @Test
    public void checkShelfCapacity(){

        Product P1 = new Product(1148, "milk", Category.MILK);

        DomainException exception = assertThrows(DomainException.class, () ->{new ProductPosition(S1, P1, 110);});
        assertEquals("Shelf cannot host more products than its capacity", exception.getMessage());
    }

    // Order cannot be started till one hour before pickup
    @Test
    public void orderCannotYetStart(){

        // Create a new Order That Will Start In 3 Hours From Now
        LocalDate orderDate = LocalDate.now();
        LocalDateTime orderDateWindow = LocalDateTime.now().plusHours(3);
        Order order = new Order(orderDate,orderDateWindow, customer);

        DomainException exception = assertThrows(DomainException.class, () ->{order.setEmployee(employee);});
        assertEquals("Order Cannot Yet Start", exception.getMessage());
    }

    @Test
    public void testBestRouting(){

        List<String> products = new ArrayList<>(order.bestRouting());
        assertNotNull(products);
        assertEquals("chocolate", products.get(0));
        assertEquals("milk", products.get(1));
    }

    @Test
    public void changePickupDetails(){
        order.setPickup(LocalDateTime.now(), PaymentMethod.CASH);
        assertEquals(PaymentMethod.CASH, order.getPickup().getPaymentMethod());
    }

    @Test
    public void calculateCostOrderWithNoProducts(){

        order2.setOrderStartWindow(LocalDateTime.now().plusMinutes(15));
        DomainException exception = assertThrows(DomainException.class, () ->{order2.calculateOrderCost(OL1);});
        assertEquals("Cannot Calculate Cost, No Products In Order Yet", exception.getMessage());

        DomainException exception2 = assertThrows(DomainException.class, () ->{order2.calculateOrderPoints(OL1);});
        assertEquals("Cannot Calculate Points, No Products In Order Yet", exception2.getMessage());
    }
}
