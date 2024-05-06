package gr.aueb.supermarket.domain;


import gr.aueb.supermarket.common.Category;
import gr.aueb.supermarket.common.Gender;
import gr.aueb.supermarket.common.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PickupTest {

    Order order;
    ShelfContainer SC1;
    ShelfContainer SC2;
    Shelf S1;
    Shelf S2;
    Customer customer;
    Product P;
    ProductPosition PP;
    OrderLine ol;

    Pickup pickup;

    @BeforeEach
    public void setup(){

        // Dates And Addresses
        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        Address address = new Address("Evrou", 5, "Megara","19100" );
        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(15);
        LocalDate orderDate = LocalDate.now();
        UserInfo userInfo = new UserInfo("nck", "0000");

        //Shelves
        SC1 = new ShelfContainer(1,2);
        SC2 = new ShelfContainer(2,2);
        S1 = SC1.addShelf(1, 10);
        S2 = SC2.addShelf(2, 100);

        //Product
        P = new Product(5, "milk", Category.MILK);
        P.setProductPosition(2, S1);

        //Customer
        customer = new Customer("Stentoumis","Nikos","partsinevelosm@gmail.com","6978600994",birthDate, Gender.MALE, userInfo);

        //Order
        order = new Order(orderDate,orderDateWindow, customer);
        ol = order.addOrderLine(P, 1);
        pickup = new Pickup(order, orderDateWindow, PaymentMethod.CASH);
    }

    @Test
    public void GettersSetter(){
        pickup.getId();
        pickup.getPickupDate();
        assertEquals("Stentoumis", pickup.getOrder().getOrderingCustomer().getFirstname());

        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(15);
        LocalDate orderDate = LocalDate.now();
        Order order = new Order(orderDate,orderDateWindow, customer);
        pickup.setOrder(order);
        pickup.setPaymentMethod(PaymentMethod.CARD);
        assertEquals(PaymentMethod.CARD, pickup.getPaymentMethod());
        LocalDateTime date = LocalDateTime.now().plusHours(15);
        DomainException exception = assertThrows(DomainException.class, () ->{pickup.setPickupDate(date);});
        assertEquals("Invalid PickUpDate", exception.getMessage());

    }

    @Test
    public void denyWrongPickUpDate(){

        LocalDateTime now = LocalDateTime.now().plusHours(5);

        DomainException exception = assertThrows(DomainException.class, () ->{order.setPickup(now, PaymentMethod.CARD);});
        assertEquals("Invalid PickUpDate", exception.getMessage());
    }
}
