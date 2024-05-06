package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import gr.aueb.supermarket.common.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderLineTest {

    Product product;
    Order order;
    OrderLine ol;
    ShelfContainer SC;
    Shelf S;
    ProductPosition PP;
    Customer customer;

    @BeforeEach
    public void setup(){

        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        LocalDateTime orderDateWindow = LocalDateTime.of(2024, 2, 1, 12, 30, 0);
        LocalDate orderDate = LocalDate.now();
        UserInfo userInfo = new UserInfo("nck", "0000");

        //Customer
        customer = new Customer("Manolis","Partsinevelos","partsinevelosm@gmail.com","6978600994",birthDate, Gender.MALE, userInfo);

        //Order
        order = new Order(orderDate,orderDateWindow, customer);

        //Shelf
        SC = new ShelfContainer(2, 1);
        S = SC.addShelf(2, 50);

        //Product
        product = new Product(5, "stake", Category.MEAT);
        product.setProductPosition(30, S);

        //OrderLine
        ol = order.addOrderLine(product, 5);

    }

    @Test
    public void GettersSetters(){
        assertNotNull(ol.getProduct());
        assertEquals(5, ol.getQuantity());
        assertNotNull(ol.getOrder());
        ol.setOrder(order);
    }


    @Test
    public void quantityChecks(){

        LocalDateTime orderDateWindow = LocalDateTime.of(2024, 2, 1, 12, 30, 0);
        LocalDate orderDate = LocalDate.now();
        Order order = new Order(orderDate,orderDateWindow, customer);
        OrderLine orderline = order.addOrderLine(product, 5);

        Product product2 = new Product(8, "Potatoes", Category.VEGETABLES);

        //Cannot accept negative quantity
        DomainException exception1 = assertThrows(DomainException.class, () ->{order.addOrderLine(product2, -5);});
        assertEquals("Quantity Cannot Be Less Than Zero", exception1.getMessage());

        //Cannot accept product with no quanity
        DomainException exception2 = assertThrows(DomainException.class, () ->{order.addOrderLine(product2, 5);});
        assertEquals("Product Has No Quantity Cannot Add To Order", exception2.getMessage());

        DomainException exception4 = assertThrows(DomainException.class, () ->{orderline.addQuantity(-2);});
        assertEquals("Quantity Cannot Be Less Than Zero", exception4.getMessage());

        ol.addQuantity(1);
    }

    //Checking remove quantity method
    @Test
    public void  checkRemoveQuantity(){
        ol.removeQuantity(2);
        assertEquals(3, ol.getQuantity());

        DomainException exception1 = assertThrows(DomainException.class, () ->{ol.removeQuantity(-2);});
        assertEquals("Quantity Cannot Be Less Than Zero", exception1.getMessage());

        DomainException exception2 = assertThrows(DomainException.class, () ->{ol.removeQuantity(1000);});
        assertEquals("Cannot Remove More Than i Have", exception2.getMessage());
    }

    // Cannot add to order more products than shelf's quantity
    @Test
    public void denyOrderMoreThanExisting(){

        DomainException exception = assertThrows(DomainException.class, () ->{ ol.addQuantity(100);});
        assertEquals("Shelf Does Not Have So Many Products", exception.getMessage());
    }

    @Test
    public void denyOrderLineWhenQuantityNull(){

        Product product2 = new Product(1.15, "ice cream", Category.SNACKS);
        DomainException exception = assertThrows(DomainException.class, () ->{OrderLine orderLine = order.addOrderLine(product2, 12);});
        assertEquals("Product Has No Quantity Cannot Add To Order", exception.getMessage());
    }

    @Test
    public void orderAndCapacity(){

        DomainException exception = assertThrows(DomainException.class, () ->{OrderLine orderLine = new OrderLine(product, 1000, order);});
        assertEquals("Order quantity cannot be more than current shelf quantity", exception.getMessage());
    }
}
