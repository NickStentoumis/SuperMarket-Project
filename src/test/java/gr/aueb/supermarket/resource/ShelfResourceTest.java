package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.representation.ShelfContainerMapper;
import gr.aueb.supermarket.representation.ShelfRepresentation;
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
public class ShelfResourceTest extends IntegrationBase {
    @Inject
    EntityManager em;

    @Inject
    ShelfContainerMapper shelfContainerMapper;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        ShelfRepresentation sr = when().get(Fixture.API_ROOT + SuperMarketURI.SHELVES + "/" + 2)
                .then()
                .statusCode(200)
                .extract().as(ShelfRepresentation.class);
        assertEquals(2, sr.id);
    }

    @Test
    public void findall(){
        List<ShelfRepresentation> shelfRepresentationList = when().get(Fixture.API_ROOT + SuperMarketURI.SHELVES)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", ShelfRepresentation.class);
        assertEquals(40, shelfRepresentationList.size());
    }

}
