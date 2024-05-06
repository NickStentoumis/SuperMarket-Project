package gr.aueb.supermarket.domain;


import gr.aueb.supermarket.common.Category;
import gr.aueb.supermarket.common.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeTest {

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
    Employee employee;

    Pickup pickup;
    @BeforeEach
    public void setup(){

        //General BirthDates And Dates
        LocalDate birthDate = LocalDate.of(2024, 2, 10);
        LocalDateTime orderDateWindow = LocalDateTime.now().plusMinutes(15);
        LocalDate today = LocalDate.now();
        LocalDate orderDate = LocalDate.now();
        Address address = new Address("Evrou", 5, "Megara", "19100");
        UserInfo userInfo = new UserInfo("nck", "0000");

        //Shelves And Shelf Containers
        SC1 = new ShelfContainer(1,2);
        SC2 = new ShelfContainer(2,2);
        S1 = SC1.addShelf(1, 10);
        S2 = SC2.addShelf(2, 100);

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



        employee = new Employee("Nick", "Stentoumis", "nstm@yahoo.com", "6982948593", birthDate, Gender.MALE, address, "Sales", "Employee", today, userInfo);
        order.setEmployee(employee);
    }

    @Test
    public void GettersSetters(){
        assertEquals("Sales", employee.getDepartment());
        assertNotNull(employee.getAddress());
        employee.setDepartment("HR");
        assertEquals("HR", employee.getDepartment());
        employee.setPosition("CEO");
        assertEquals("CEO", employee.getPosition());
        assertNotNull(employee.getHiringDate());
        LocalDate now = LocalDate.now();
        employee.setHiringDate(now);
        employee.getAddress().setCity("Megara");
        assertEquals("Megara", employee.getAddress().getCity());
        employee.getAddress().setStreet("MZ");
        assertEquals("MZ", employee.getAddress().getStreet());
        employee.getAddress().setStreetNumber(1);
        assertEquals(1, employee.getAddress().getStreetNumber());
        employee.getAddress().setZipcode("20349");
        assertEquals("20349", employee.getAddress().getZipcode());
    }
}
