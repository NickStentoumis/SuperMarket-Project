package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import gr.aueb.supermarket.common.CustomerTier;
import gr.aueb.supermarket.common.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    //Objects We Are Going To Need
    Order order;
    ShelfContainer SC1;
    ShelfContainer SC2;
    Shelf S1;
    Shelf S2;
    Product P1;
    Product P2;
    ProductPosition PP1;
    ProductPosition PP2;
    OrderLine OL1;
    OrderLine OL2;

    Customer customer;

    @BeforeEach
    public void setup(){

        //General BirthDates And Dates
        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(15);
        LocalDate orderDate = LocalDate.now();
        UserInfo userInfo = new UserInfo("nck", "0000");

        //Shelves And Shelf Containers
        SC1 = new ShelfContainer(1,2);
        SC2 = new ShelfContainer(2,2);
        S1 = SC1.addShelf(1,10);
        S2 = SC2.addShelf(2,100);

        //Customer
        customer = new Customer("Manolis","Partsinevelos","partsinevelosm@gmail.com","6978600994",birthDate, Gender.MALE, userInfo);

        //Order
        order = new Order(orderDate,orderDateWindow, customer);

        //Two Products On S2
        P1 = new Product(10, "milk", Category.MILK);
        P2 = new Product(5, "chicken", Category.MEAT);
        PP1 = P1.setProductPosition(20, S2);
        PP2 = P2.setProductPosition(20, S2);

        //Adding Products To Order
        OL1 = order.addOrderLine(P1, 2);
        OL2 = order.addOrderLine(P2, 2);
    }

    @Test
    public void GettersSetters(){

        assertEquals(5, customer.getPoints());
        customer.addPoints(1);
        assertEquals(6, customer.getPoints());
        assertEquals(CustomerTier.BRONZE, customer.getTier());
        customer.setTier(CustomerTier.SILVER);
        assertEquals(CustomerTier.SILVER, customer.getTier());
        assertEquals(50, customer.getMoneySpent());
        customer.setMoneySpent(5);
        assertEquals(55, customer.getMoneySpent());
        assertEquals("Manolis", customer.getFirstname());
        customer.setFirstname("Nick");
        assertEquals("Partsinevelos", customer.getLastname());
        customer.setLastname("Stentoumis");
        assertEquals("partsinevelosm@gmail.com", customer.getEmail());
        customer.setEmail("test@test.gr");
        assertEquals("6978600994", customer.getPhoneNumber());
        customer.setPhoneNumber("6980000392");
        assertNotNull(customer.getDateOfBirth());
        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        customer.setDateOfBirth(birthDate);
        assertEquals(Gender.MALE, customer.getGender());
        customer.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, customer.getGender());
        assertEquals("nck", customer.getUsername());
        assertEquals("0000", customer.getUserInfo().getPassword());
        customer.getUserInfo().setPassword("1111");
        assertEquals("1111", customer.getUserInfo().getPassword());
        customer.getUserInfo().setUsername("nick");
        customer.setPoints(6);
        assertEquals(6, customer.getPoints());
    }

    @Test
    public void MakePointsToMoney(){

        assertEquals(0.05, customer.pointsToMoney());
    }

    @Test
    public void checkCustomerTier(){

        //Creation of a New Expensive
        Product P1 = new Product(1100, "chicken", Category.MEAT);
        PP1 = P1.setProductPosition(10, S2);

        //Adding New Product To Order
        OL1 = order.addOrderLine(P1, 1);

        customer.checkCustomerTier();
        assertEquals(CustomerTier.SILVER, customer.getTier());
    }
}
