package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.representation.ShelfContainerRepresentation;
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
public class ShelfContainerResourceTest extends IntegrationBase {
    @Inject
    EntityManager em;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        ShelfContainerRepresentation scr = when().get(Fixture.API_ROOT + SuperMarketURI.SHELFCONTAINERS + "/" + 11)
                .then()
                .statusCode(200)
                .extract().as(ShelfContainerRepresentation.class);
        assertEquals(11, scr.id);
    }

    @Test
    public void findall(){
        List<ShelfContainerRepresentation> scrL = when().get(Fixture.API_ROOT + SuperMarketURI.SHELFCONTAINERS)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", ShelfContainerRepresentation.class);
        assertEquals(10, scrL.size());
    }
}
