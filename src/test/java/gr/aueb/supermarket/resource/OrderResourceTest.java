package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.domain.Customer;
import gr.aueb.supermarket.domain.Order;
import gr.aueb.supermarket.domain.Product;
import gr.aueb.supermarket.representation.*;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderResourceTest {
    @Inject
    EntityManager em;

    @Inject
    ProductMapper productMapper;

    @Inject
    OrderMapper orderMapper;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        OrderRepresentation orderRepresentation = when().get(Fixture.API_ROOT + SuperMarketURI.ORDERS + "/" + 2)
                .then()
                .statusCode(200)
                .extract().as(OrderRepresentation.class);
        LocalDate date = LocalDate.of(1990, 1, 1);
        //assertEquals(date, orderRepresentation.orderDate);
    }

    @Test
    public void findall(){
        List<OrderRepresentation> orderRepresentationList = when().get(Fixture.API_ROOT + SuperMarketURI.ORDERS)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", OrderRepresentation.class);
        assertEquals(10, orderRepresentationList.size());
    }

    @Test
    public void addOrderLineTest(){
        OrderLineRepresentation orderLineRepresentation = new OrderLineRepresentation();
        orderLineRepresentation.quantity = 2;

        Product product = em.find(Product.class, 20L);
        assertNotNull(product);

        Order order = em.find(Order.class, 11L);
        assertNotNull(order);

        Long orderId = order.getId();

        ProductRepresentation productRepresentation = productMapper.toRepresentation(product);
        productRepresentation.id = product.getID();
        orderLineRepresentation.productId = productRepresentation.id;
        product.getProductPosition().GetShelf().removeCapacity(10);


        System.out.println(Fixture.API_ROOT + SuperMarketURI.ORDERS +  "/" + orderId + SuperMarketURI.ORDERLINES);

        OrderRepresentation orderRepresentation1 = given()
                .contentType("application/json")
                .body(orderLineRepresentation)
                .when()
                .post(Fixture.API_ROOT + SuperMarketURI.ORDERS +  "/" + orderId + SuperMarketURI.ORDERLINES)
                .then()
                .statusCode(200)
                .extract().as(OrderRepresentation.class);

        assertEquals(3, order.getOrderlines().size());
    }

    @Test
    public void getPickup(){
        PickupRepresentation pickupRepresentation =  when().get(Fixture.API_ROOT + SuperMarketURI.ORDERS + "/" + 2 + SuperMarketURI.PICKUPS)
                .then()
                .statusCode(200)
                .extract().as(PickupRepresentation.class);
        LocalDate date = LocalDate.of(1990, 1, 1);
        assertNotNull(pickupRepresentation);
        assertEquals("CASH", pickupRepresentation.paymentMethod);
    }

    @Test
    public void orderCreationTest(){
       OrderRepresentation orderRepresentation = new OrderRepresentation();

       LocalDate date = LocalDate.of(1990, 1, 1);
        LocalDateTime dateTime = LocalDateTime.now();
       orderRepresentation.orderDate = date.toString();
       orderRepresentation.orderStartWindow = dateTime.toString();
       Customer customer = em.find(Customer.class, 1L);
       assertNotNull(customer);
       orderRepresentation.orderingCustomerId = customer.getID();

        OrderRepresentation orderRepresentation1 =
                given()
                        .contentType("application/json")
                        .body(orderRepresentation)
                        .when()
                        .post(Fixture.API_ROOT + SuperMarketURI.ORDERS)
                        .then()
                        .statusCode(201)
                        .extract().as(OrderRepresentation.class);

        Query query = em.createQuery("select o from Order o");
        List<Order> result = query.getResultList();

        assertEquals(11, result.size());

    }
}
