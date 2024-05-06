package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.representation.CustomerRepresentation;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CustomerResourceTest extends IntegrationBase {

    @Inject
    EntityManager em;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        CustomerRepresentation customerRepresentation = when().get(Fixture.API_ROOT + SuperMarketURI.CUSTOMERS + "/" + 2)
                .then()
                .statusCode(200)
                .extract().as(CustomerRepresentation.class);
        assertEquals("Nikolaos", customerRepresentation.firstname);
    }

    @Test
    public void findall(){
        List<CustomerRepresentation> customerRepresentationlist = when().get(Fixture.API_ROOT + SuperMarketURI.CUSTOMERS)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", CustomerRepresentation.class);
        assertEquals(5, customerRepresentationlist.size());
        assertEquals("Manolis", customerRepresentationlist.get(0).firstname);
    }

}
