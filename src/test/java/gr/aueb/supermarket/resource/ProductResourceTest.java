package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.Fixture;
import gr.aueb.supermarket.domain.Product;
import gr.aueb.supermarket.representation.ProductPositionRepresentation;
import gr.aueb.supermarket.representation.ProductRepresentation;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ProductResourceTest extends IntegrationBase {

    @Inject
    EntityManager em;

    @BeforeAll
    public static void setUp() {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void find(){
        ProductRepresentation productRepresentation = when().get(Fixture.API_ROOT + SuperMarketURI.PRODUCTS + "/" + 21)
                .then()
                .statusCode(200)
                .extract().as(ProductRepresentation.class);
        assertEquals("Lays", productRepresentation.name);
    }

    @Test
    public void findall(){
        List<ProductRepresentation> productRepresentationlist = when().get(Fixture.API_ROOT + SuperMarketURI.PRODUCTS)
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList(".", ProductRepresentation.class);
        assertEquals(20, productRepresentationlist.size());
    }

    @Test
    public void testProductCreation(){
        ProductRepresentation productRepresentation = new ProductRepresentation();
        productRepresentation.name = "Skittles";
        productRepresentation.price = 1.15;

        ProductRepresentation productRepresentation1 =
        given()
                .contentType("application/json")
                .body(productRepresentation)
                .when()
                .post(Fixture.API_ROOT + SuperMarketURI.PRODUCTS)
                .then()
                .statusCode(201)// Verify the Location header
                .extract().as(ProductRepresentation.class);

        assertEquals("Skittles", productRepresentation1.name);
        assertEquals(1.15, productRepresentation1.price);
    }

    @Test
    public void changeProductPrice(){
        double newPrice = 20;

        ProductRepresentation productRepresentation1 =
                given()
                        .contentType("application/json")
                        .body(newPrice)
                        .when()
                        .post(Fixture.API_ROOT + SuperMarketURI.PRODUCTS + "/" + 21)
                        .then()
                        .statusCode(201)// Verify the Location header
                        .extract().as(ProductRepresentation.class);

        assertEquals("Lays", productRepresentation1.name);
        assertEquals(20, productRepresentation1.price);

        Query query = em.createQuery("select j from Product j where j.id = 21");
        List<Product> result = query.getResultList();
        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getPrice());
    }

    @Test
    public void addProductPositionTest(){
        ProductRepresentation productRepresentation = new ProductRepresentation();
        productRepresentation.name = "Skittles2";
        productRepresentation.price = 1.20;

        ProductRepresentation productRepresentation1 =
                given()
                        .contentType("application/json")
                        .body(productRepresentation)
                        .when()
                        .post(Fixture.API_ROOT + SuperMarketURI.PRODUCTS)
                        .then()
                        .statusCode(201)// Verify the Location header
                        .extract().as(ProductRepresentation.class);

        assertEquals("Skittles2", productRepresentation1.name);
        assertEquals(1.20, productRepresentation1.price);

        Product product = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", "Skittles2")
                .getSingleResult();

        assertEquals(1.20, product.getPrice());

        ProductPositionRepresentation productPositionRepresentation = new ProductPositionRepresentation();

        productPositionRepresentation.shelfID =  10;
        productPositionRepresentation.quantity = 10;
        int id = product.getID();
        ProductRepresentation productRepresentation3 = given()
                .contentType("application/json")
                .body(productPositionRepresentation)
                .when()
                .post(Fixture.API_ROOT + SuperMarketURI.PRODUCTS +  "/" + id + SuperMarketURI.PRODUCTPOSITION)
                .then()
                .statusCode(200)
                .extract().as(ProductRepresentation.class);


        assertEquals(10, product.getProductPosition().GetQuantity());
        assertEquals(90, product.getProductPosition().GetShelf().GetCapacity());
    }

    @Test
    public void getQuantity(){
        Integer quantity = when().get(Fixture.API_ROOT + SuperMarketURI.PRODUCTS + "/" + 21 + "/quantity")
                .then()
                .statusCode(200)
                .extract().as(Integer.class);
        assertEquals(20, quantity);
    }
}
