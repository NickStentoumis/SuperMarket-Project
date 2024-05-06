package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.representation.EmployeeRepresentation;
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
public class EmployeeResourceTest  extends IntegrationBase {
    @Inject
    EntityManager em;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        EmployeeRepresentation er = when().get(Fixture.API_ROOT + SuperMarketURI.EMPLOYEES + "/" + 8)
                .then()
                .statusCode(200)
                .extract().as(EmployeeRepresentation.class);
        assertEquals("Dimitris", er.firstname);
    }

    @Test
    public void findall(){
        List<EmployeeRepresentation> erl = when().get(Fixture.API_ROOT + SuperMarketURI.EMPLOYEES)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", EmployeeRepresentation.class);
        assertEquals(3, erl.size());
    }
}
